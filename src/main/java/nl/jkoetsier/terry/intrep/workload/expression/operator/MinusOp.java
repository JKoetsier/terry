package nl.jkoetsier.terry.intrep.workload.expression.operator;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;

public class MinusOp extends ArithmeticOperator {

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
