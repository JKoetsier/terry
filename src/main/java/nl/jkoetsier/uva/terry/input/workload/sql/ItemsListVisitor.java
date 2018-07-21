package nl.jkoetsier.uva.terry.input.workload.sql;

import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitorAdapter;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.statement.select.SubSelect;
import nl.jkoetsier.uva.terry.intrep.workload.expression.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemsListVisitor extends ItemsListVisitorAdapter {

  private static Logger logger = LoggerFactory.getLogger(ItemsListVisitor.class);

  private Expression expression;

  public Expression getExpression() {
    return expression;
  }

  @Override
  public void visit(SubSelect subSelect) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    expressionVisitor.visit(subSelect);
    expression = expressionVisitor.getExpression();
  }

  @Override
  public void visit(ExpressionList expressionList) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    expressionVisitor.visit(expressionList);
    expression = expressionVisitor.getExpression();
  }

  @Override
  public void visit(MultiExpressionList multiExprList) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    expressionVisitor.visit(multiExprList);
    expression = expressionVisitor.getExpression();
  }
}
