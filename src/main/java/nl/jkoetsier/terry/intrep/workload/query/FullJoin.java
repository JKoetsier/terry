package nl.jkoetsier.terry.intrep.workload.query;

import nl.jkoetsier.terry.intrep.workload.expression.Expression;
import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;

public class FullJoin extends RAJoin {

  public FullJoin(Relation leftInput, Relation rightInput, Expression onExpression) {
    super(leftInput, rightInput, onExpression);
  }

  public FullJoin(Relation leftInput, Relation rightInput) {
    super(leftInput, rightInput);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    leftInput.acceptVisitor(workloadVisitor);
    rightInput.acceptVisitor(workloadVisitor);

    if (onExpression != null) {
      onExpression.acceptVisitor(workloadVisitor);
    }

    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "FullJoin{" +
        "onExpression=" + onExpression +
        ", leftInput=" + leftInput +
        ", rightInput=" + rightInput +
        '}';
  }
}
