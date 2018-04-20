package nl.jkoetsier.uva.dbbench.workload.expression.operator;

import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadVisitor;

public class AndOp extends Operator {

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {

    workloadVisitor.visit(this);
  }
}
