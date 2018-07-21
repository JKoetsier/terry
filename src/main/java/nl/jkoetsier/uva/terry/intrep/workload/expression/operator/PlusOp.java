package nl.jkoetsier.uva.terry.intrep.workload.expression.operator;

import nl.jkoetsier.uva.terry.intrep.workload.visitor.WorkloadVisitor;

public class PlusOp extends ArithmeticOperator {

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}