package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class IsNullExpr extends Expression {

  private Expression leftExpression;
  private boolean isNot;

  public IsNullExpr(Expression leftExpression, boolean isNot) {
    this.leftExpression = leftExpression;
    this.isNot = isNot;
  }

  public Expression getLeftExpression() {
    return leftExpression;
  }

  public boolean isNot() {
    return isNot;
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
}
