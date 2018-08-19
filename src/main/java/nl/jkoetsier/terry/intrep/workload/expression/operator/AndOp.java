package nl.jkoetsier.terry.intrep.workload.expression.operator;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;

public class AndOp extends LogicalOperator {

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
