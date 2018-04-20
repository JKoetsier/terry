package nl.jkoetsier.uva.dbbench.workload.expression;

import nl.jkoetsier.uva.dbbench.workload.query.FieldRef;
import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadVisitor;

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
