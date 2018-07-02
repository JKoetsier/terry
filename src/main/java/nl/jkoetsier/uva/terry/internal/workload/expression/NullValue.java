package nl.jkoetsier.uva.terry.internal.workload.expression;

import nl.jkoetsier.uva.terry.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.terry.internal.workload.visitor.WorkloadVisitor;

public class NullValue extends Expression {

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }

  @Override
  public void validate(ExposedFields exposedFields) {

  }
}
