package nl.jkoetsier.terry.intrep.workload.expression;

import nl.jkoetsier.terry.intrep.workload.expression.operator.Operator;
import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;

public class BinExpression extends Expression {

  private Expression leftExpr;
  private Expression rightExpr;
  private Operator operator;

  public BinExpression(Expression leftExpr, Expression rightExpr, Operator operator, boolean not) {
    super(not);
    this.leftExpr = leftExpr;
    this.rightExpr = rightExpr;
    this.operator = operator;
  }

  public Expression getLeftExpr() {
    return leftExpr;
  }

  public void setLeftExpr(Expression leftExpr) {
    this.leftExpr = leftExpr;
  }

  public Expression getRightExpr() {
    return rightExpr;
  }

  public void setRightExpr(Expression rightExpr) {
    this.rightExpr = rightExpr;
  }

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    leftExpr.acceptVisitor(workloadVisitor);
    rightExpr.acceptVisitor(workloadVisitor);
    operator.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "BinExpression{" +
        "leftExpr=" + leftExpr +
        ", rightExpr=" + rightExpr +
        ", operator=" + operator +
        '}';
  }
}
