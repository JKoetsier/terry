package nl.jkoetsier.uva.dbbench.workload.query;

import java.util.List;
import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadTreeElement;

public abstract class Relation implements WorkloadTreeElement {

  public abstract FieldRef getFieldRef(String fieldName);

  public abstract FieldRef getFieldRef(String tableName, String fieldName);

  public abstract List<FieldRef> getFieldRefsForTable(String tableName);

  public boolean hasFieldRef(String fieldName) {
    return getFieldRef(fieldName) != null;
  }

  public boolean hasFieldRef(String tableName, String fieldName) {
    return getFieldRef(tableName, fieldName) != null;
  }
}
