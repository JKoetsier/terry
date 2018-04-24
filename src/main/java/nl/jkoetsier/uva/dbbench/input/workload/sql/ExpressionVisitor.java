package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.schema.Column;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.BinExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Expression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FieldExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.DateConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.DoubleConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.LongConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.StringConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.Operator;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.OperatorFactory;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;

public class ExpressionVisitor extends ExpressionVisitorAdapter {

  private Expression expression;
  private Relation relation;

  // Relation to check for variables etc
  public ExpressionVisitor(Relation relation) {
    expression = null;
    this.relation = relation;
  }

  public Expression getExpression() {
    return expression;
  }

  public void reset() {
    expression = null;
  }

  @Override
  public void visit(DoubleValue value) {
    expression = new DoubleConstant(value.getValue());
  }

  @Override
  public void visit(LongValue value) {
    expression = new LongConstant(value.getValue());
  }

  @Override
  public void visit(DateValue value) {
    expression = new DateConstant(value.getValue());
  }

  @Override
  public void visit(StringValue value) {
    expression = new StringConstant(value.getValue());
  }

  @Override
  public void visit(Column column) {
    expression = new FieldExpression(column.getFullyQualifiedName());
  }

  @Override
  protected void visitBinaryExpression(BinaryExpression expr) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor(relation);
    expr.getLeftExpression().accept(expressionVisitor);
    Expression leftExpr = expressionVisitor.getExpression();

    expressionVisitor.reset();

    expr.getRightExpression().accept(expressionVisitor);
    Expression rightExpr = expressionVisitor.getExpression();

    Operator operator = OperatorFactory.create(expr.getStringExpression());

    expression = new BinExpression(
        leftExpr,
        rightExpr,
        operator,
        expr.isNot()
    );
  }
}
