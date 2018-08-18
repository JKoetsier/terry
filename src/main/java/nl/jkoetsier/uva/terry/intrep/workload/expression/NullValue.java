package nl.jkoetsier.uva.terry.intrep.workload.expression;

import nl.jkoetsier.uva.terry.intrep.workload.visitor.WorkloadVisitor;

public class NullValue extends Expression {

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
