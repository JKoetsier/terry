package nl.jkoetsier.uva.terry.intrep.workload.query;

import nl.jkoetsier.uva.terry.intrep.workload.expression.Expression;
import nl.jkoetsier.uva.terry.intrep.workload.visitor.WorkloadVisitor;

public class InnerJoin extends RAJoin {

  public InnerJoin(Relation leftInput, Relation rightInput, Expression onExpression) {
    super(leftInput, rightInput, onExpression);
  }

  public InnerJoin(Relation leftInput, Relation rightInput) {
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
    return "InnerJoin{" +
        "onExpression=" + onExpression +
        ", leftInput=" + leftInput +
        ", rightInput=" + rightInput +
        '}';
  }
}
