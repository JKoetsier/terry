package nl.jkoetsier.uva.dbbench.connector.mssql.workload;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nl.jkoetsier.uva.dbbench.internal.workload.element.OrderBy;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FieldExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.SelectExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FullJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.InputRelation;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Projection;
import nl.jkoetsier.uva.dbbench.connector.SqlWorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsSqlWorkloadVisitor extends SqlWorkloadVisitor {

  static Logger logger = LoggerFactory.getLogger(MsSqlWorkloadVisitor.class);

  @Override
  public void visit(SelectExpression selectExpression) {
    logger.debug("Visit SelectExpression");

    String alias = "";

    if (selectExpression.getAlias() != null) {
      alias = String.format(" AS [%s]", selectExpression.getAlias());
    }

    currentStack.push(String.format("%s%s", currentStack.pop(), alias));
  }

  @Override
  public void visit(FieldExpression fieldExpression) {
    logger.debug("Visit FieldExpression");

    String[] fieldName = fieldExpression.getFieldName().split("\\.");

    currentStack.push(String.format("[%s]", String.join("].[", fieldName)));
  }


  @Override
  public void visit(InputRelation inputRelation) {
    logger.debug("Visit InputRelation");

    if (inputRelation.getTableAlias() != null) {
      currentStack.push(String.format("[%s] AS [%s]", inputRelation.getTableName(),
          inputRelation.getTableAlias()));
    } else {
      currentStack.push(String.format("[%s]", inputRelation.getTableName()));
    }
  }

  @Override
  public void visit(Projection projection) {
    logger.debug("Visit Projection");

    String top = "";
    String orderBy = "";
    String offset = "";

    if (projection.getLimit() != null) {
      String topStack = currentStack.pop();
      top = String.format(" TOP(%s)", topStack);

      if (projection.getOffset() != null) {
        top = "";
        offset = String.format(" OFFSET %s FETCH %s", top, currentStack.pop());
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
        "SELECT%s %s FROM %s%s%s",
        top,
        select,
        from,
        orderBy,
        offset
    );

    currentStack.push(str);
  }
}
