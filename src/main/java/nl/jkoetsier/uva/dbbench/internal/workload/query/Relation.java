package nl.jkoetsier.uva.dbbench.internal.workload.query;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.internal.schema.Table;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadElement;

public abstract class Relation implements WorkloadElement {

  protected ExposedFields exposedFields;
  protected boolean isValidated = false;

  public abstract ExposedFields getExposedFields();
  public abstract HashMap<Table, Integer> getTouchedTables();

  public boolean isValidated() {
    return isValidated;
  }

  public void setValidated(boolean isValidated) {
    this.isValidated = isValidated;
  }
}
