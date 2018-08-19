package nl.jkoetsier.terry.intrep.workload.expression;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;

public class NullValue extends Expression {

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
