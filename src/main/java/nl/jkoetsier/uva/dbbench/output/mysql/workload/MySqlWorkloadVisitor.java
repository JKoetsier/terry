package nl.jkoetsier.uva.dbbench.output.mysql.workload;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nl.jkoetsier.uva.dbbench.internal.workload.element.OrderBy;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FieldExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.SelectExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FullJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.InputRelation;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Projection;
import nl.jkoetsier.uva.dbbench.output.BaseSqlWorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlWorkloadVisitor extends BaseSqlWorkloadVisitor {

  private static Logger logger = LoggerFactory.getLogger(MySqlWorkloadVisitor.class);

  @Override
  public void visit(SelectExpression selectExpression) {
    logger.debug("Visit SelectExpression");

    String alias = "";

    if (selectExpression.getAlias() != null) {
      alias = String.format(" AS %s", selectExpression.getAlias());
    }

    currentStack.push(String.format("%s%s", currentStack.pop(), alias));
  }

  @Override
  public void visit(FieldExpression fieldExpression) {
    logger.debug("Visit FieldExpression");

    String[] fieldName = fieldExpression.getFieldName().split("\\.");

    currentStack.push(String.format("%s", String.join(".", fieldName)));
  }

  @Override
  public void visit(FullJoin fullJoin) {
    throw new RuntimeException("Not supported in MySQL. Do something with this");
  }

  @Override
  public void visit(InputRelation inputRelation) {
    logger.debug("Visit InputRelation");

    if (inputRelation.getTableAlias() != null) {
      currentStack.push(String.format("%s AS %s", inputRelation.getTableName(),
          inputRelation.getTableAlias()));
    } else {
      currentStack.push(String.format("%s", inputRelation.getTableName()));
    }
  }

  @Override
  public void visit(Projection projection) {
    logger.debug("Visit Projection");

    String limit = "";
    String orderBy = "";

    if (projection.getLimit() != null) {
      limit = String.format(" LIMIT %s", currentStack.pop());
    }

    if (projection.getOrderBy() != null) {
      List<String> orderByList = new ArrayList<>();

      for (OrderBy orderByElm : projection.getOrderBy()) {

        orderByList.add(String.format("%s %s", orderByElm.getFieldExpression().getFieldName(),
            orderByElm.getDirection()));
      }

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
