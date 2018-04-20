package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.Operator;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class BinExpression extends Expression {

  private Expression leftExpr;
  private Expression rightExpr;
  private Operator operator;
  private boolean not;

  public BinExpression(Expression leftExpr, Expression rightExpr, Operator operator, boolean
      not) {
    this.leftExpr = leftExpr;
    this.rightExpr = rightExpr;
    this.operator = operator;
    this.not = not;
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

  public boolean isNot() {
    return not;
  }

  public void setNot(boolean not) {
    this.not = not;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    leftExpr.acceptVisitor(workloadVisitor);
    rightExpr.acceptVisitor(workloadVisitor);
    operator.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }
}
