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
  public void visit(FullJoin fullJoin) {
    logger.debug("Visit FullJoin");

    String onExpr = "";

    if (fullJoin.getOnExpression() != null) {
      onExpr = String.format(" ON %s", currentStack.pop());
    }

    String rightInp = currentStack.pop();
    String leftInp = currentStack.pop();

    currentStack.push(String.format("%s FULL JOIN %s%s", leftInp, rightInp, onExpr));
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

    if (projection.getLimit() != null) {
      top = String.format(" TOP(%s)", currentStack.pop());
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
        "SELECT%s %s FROM %s%s",
        top,
        select,
        from,
        orderBy
    );

    currentStack.push(str);
  }
}
