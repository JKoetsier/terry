package nl.jkoetsier.uva.dbbench.internal.workload.query;

import java.util.List;
import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadElement;

public abstract class Relation implements WorkloadElement {

  protected boolean isValidated = false;

  public abstract FieldRef getFieldRef(String fieldName);

  public abstract FieldRef getFieldRef(String tableName, String fieldName);

  public abstract List<FieldRef> getFieldRefsForTable(String tableName);

  public abstract void validate(Schema schema) throws NotMatchingWorkloadException;

  public boolean hasFieldRef(String fieldName) {
    return getFieldRef(fieldName) != null;
  }

  public boolean hasFieldRef(String tableName, String fieldName) {
    return getFieldRef(tableName, fieldName) != null;
  }

  public boolean isValidated() {
    return isValidated;
  }

}
