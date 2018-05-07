package nl.jkoetsier.uva.dbbench.connector.monetdb.workload;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nl.jkoetsier.uva.dbbench.connector.SqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.SelectExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Projection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonetDbWorkloadVisitor extends SqlWorkloadVisitor {

  static Logger logger = LoggerFactory.getLogger(MonetDbWorkloadVisitor.class);

  @Override
  public void visit(Projection projection) {
    logger.debug("Visit Projection");

    String limit = "";
    String orderBy = "";

    if (projection.getLimit() != null) {
      limit = String.format(" LIMIT %s", currentStack.pop());

      if (projection.getOffset() != null) {
        limit = limit.concat(String.format(" OFFSET %s", currentStack.pop()));
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
    boolean parentheses = from.startsWith("SELECT");

    from = String.format("%s%s%s", parentheses ? "(" : "",
        from,
        parentheses ? ")" : "");

    String str = String.format(
        "SELECT %s FROM %s%s%s",
        select,
        from,
        orderBy,
        limit
    );

    currentStack.push(str);
  }
}
