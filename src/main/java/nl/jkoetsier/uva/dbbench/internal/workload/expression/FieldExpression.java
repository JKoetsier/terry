package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.internal.workload.query.FieldRef;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class FieldExpression extends Expression {

  private FieldRef fieldRef;

  public FieldExpression(FieldRef fieldRef) {
    this.fieldRef = fieldRef;
  }

  public FieldRef getFieldRef() {
    return fieldRef;
  }

  public void setFieldRef(FieldRef fieldRef) {
    this.fieldRef = fieldRef;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
