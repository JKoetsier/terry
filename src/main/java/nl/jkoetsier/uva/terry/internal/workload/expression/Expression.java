package nl.jkoetsier.uva.terry.internal.workload.expression;

import nl.jkoetsier.uva.terry.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.terry.internal.workload.visitor.WorkloadElement;

public abstract class Expression implements WorkloadElement {

  private boolean not;

  public Expression(boolean not) {
    this.not = not;
  }

  public Expression() {

  }

  public boolean isNot() {
    return not;
  }

  public void setNot(boolean not) {
    this.not = not;
  }

  public ExposedFields getExposedFields() {
    return new ExposedFields();
  }

  public abstract void validate(ExposedFields exposedFields);
}
