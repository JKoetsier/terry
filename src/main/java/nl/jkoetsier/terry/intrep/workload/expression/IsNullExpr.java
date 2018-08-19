package nl.jkoetsier.terry.intrep.workload.expression;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;

public class IsNullExpr extends Expression {

  private Expression inExpression;

  public IsNullExpr(Expression inExpression, boolean isNot) {
    super(isNot);
    this.inExpression = inExpression;
  }

  public Expression getInExpression() {
    return inExpression;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    inExpression.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "IsNullExpr{" +
        "inExpression=" + inExpression +
        '}';
  }
}
