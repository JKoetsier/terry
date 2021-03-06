package nl.jkoetsier.terry.connector.mssql.workload;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nl.jkoetsier.terry.connector.SqlIdentifierQuoter;
import nl.jkoetsier.terry.connector.mssql.MsSqlIdentifierQuoter;
import nl.jkoetsier.terry.intrep.workload.expression.SelectExpression;
import nl.jkoetsier.terry.intrep.workload.query.Projection;
import nl.jkoetsier.terry.connector.SqlWorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsSqlWorkloadVisitor extends SqlWorkloadVisitor {

  static Logger logger = LoggerFactory.getLogger(MsSqlWorkloadVisitor.class);

  @Override
  protected SqlIdentifierQuoter getIdentifierQuoter() {
    return new MsSqlIdentifierQuoter();
  }

  @Override
  public void visit(Projection projection) {
    String top = "";
    String orderBy = "";
    String offset = "";

    if (projection.getLimit() != null) {
      String topStack = currentStack.pop();

      if (projection.getOffset() != null) {
        offset = String
            .format(" OFFSET %s ROWS FETCH NEXT %s ROWS ONLY", currentStack.pop(), topStack);
      } else {
        top = String.format(" TOP(%s)", topStack);
      }
    }

    if (projection.getOrderBy() != null) {
      List<String> orderByList = projection.getOrderByAsStrings();
      orderBy = String.format(" ORDER BY %s", String.join(", ", orderByList));
    }

    List<String> selectFields = new ArrayList<>();
    String select;

    if (projection.getSelectExpressions() != null) {
      for (SelectExpression selectExpression : projection.getSelectExpressions()) {
        selectFields.add(currentStack.pop());
      }
    }

    Collections.reverse(selectFields);

    if (selectFields.size() > 0) {
      select = String.join(", ", selectFields);
    } else {
      select = "*";
    }

    String from = currentStack.pop();

    String str = String.format(
        "(SELECT%s%s %s FROM %s%s%s)",
        projection.isDistinct() ? " DISTINCT" : "",
        top,
        select,
        from,
        orderBy,
        offset
    );

    currentStack.push(str);
  }
}
