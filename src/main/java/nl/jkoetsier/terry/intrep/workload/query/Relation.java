package nl.jkoetsier.terry.intrep.workload.query;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadElement;

public abstract class Relation implements WorkloadElement {

  protected boolean isValidated = false;

  public boolean isValidated() {
    return isValidated;
  }

  public void setValidated(boolean isValidated) {
    this.isValidated = isValidated;
  }
}
