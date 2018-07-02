package nl.jkoetsier.uva.terry.internal.workload.expression;

import nl.jkoetsier.uva.terry.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.terry.internal.workload.visitor.WorkloadVisitor;

public class IsNullExpr extends Expression {

  private Expression leftExpression;

  public IsNullExpr(Expression leftExpression, boolean isNot) {
    super(isNot);
    this.leftExpression = leftExpression;
  }

  public Expression getLeftExpression() {
    return leftExpression;
  }

  @Override
  public void validate(ExposedFields exposedFields) {
    leftExpression.validate(exposedFields);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    leftExpression.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "IsNullExpr{" +
        "leftExpression=" + leftExpression +
        '}';
  }
}
