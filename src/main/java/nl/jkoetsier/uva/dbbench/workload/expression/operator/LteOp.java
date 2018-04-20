package nl.jkoetsier.uva.dbbench.workload.expression.operator;

import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadVisitor;

public class LteOp extends Operator {

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }

}
