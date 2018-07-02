package nl.jkoetsier.uva.terry.internal.workload.expression.operator;

import nl.jkoetsier.uva.terry.internal.workload.visitor.WorkloadVisitor;

public class AndOp extends LogicalOperator {

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
