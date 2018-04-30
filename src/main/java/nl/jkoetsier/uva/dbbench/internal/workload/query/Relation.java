package nl.jkoetsier.uva.dbbench.internal.workload.query;

import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadElement;

public abstract class Relation implements WorkloadElement {

  protected ExposedFields exposedFields;
  protected boolean isValidated = false;

  // TODO insert Rename here?
  // Or keep all current fieldrefs somewhere?

  public abstract ExposedFields getExposedFields();

  public boolean isValidated() {
    return isValidated;
  }

  public void setValidated(boolean isValidated) {
    this.isValidated = isValidated;
  }
}
