package nl.jkoetsier.uva.terry.internal.workload.expression.operator;

import nl.jkoetsier.uva.terry.internal.workload.visitor.WorkloadVisitor;

public class GtOp extends RelationalOperator {

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
