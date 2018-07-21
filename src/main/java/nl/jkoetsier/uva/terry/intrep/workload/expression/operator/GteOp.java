package nl.jkoetsier.uva.terry.intrep.workload.expression.operator;

import nl.jkoetsier.uva.terry.intrep.workload.visitor.WorkloadVisitor;

public class GteOp extends RelationalOperator {

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
