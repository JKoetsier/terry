package nl.jkoetsier.uva.dbbench.workload.query;

import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.uva.dbbench.schema.Entity;
import nl.jkoetsier.uva.dbbench.schema.fields.Field;

public class FieldRefs {

  private List<FieldRef> fieldRefs;

  public FieldRefs() {
    fieldRefs = new ArrayList<>();
  }

  public FieldRefs(List<FieldRef> fieldRefs) {
    this.fieldRefs = fieldRefs;
  }

  public static FieldRefs create(Entity entity) {
    FieldRefs fieldRefs = new FieldRefs();

    for (Field field : entity.getFields().values()) {
      FieldRef fieldRef = new FieldRef(field, entity.getName(), field.getName());
      fieldRefs.add(fieldRef);
    }

    return fieldRefs;
  }

  public static FieldRefs create(Entity entity, String tableAlias) {
    FieldRefs fieldRefs = new FieldRefs();

    for (Field field : entity.getFields().values()) {
      FieldRef fieldRef = new FieldRef(field, entity.getName(), field.getName(), tableAlias);
      fieldRefs.add(fieldRef);
    }

    return fieldRefs;
  }

  public void add(FieldRef fieldRef) {
    fieldRefs.add(fieldRef);
  }

  public void addAll(List<FieldRef> fieldRefList) {
    fieldRefs.addAll(fieldRefList);
  }

  public int size() {
    return fieldRefs.size();
  }

  public FieldRef get(String name) {
    String[] splitOnDot = name.split("\\.");

    if (splitOnDot.length > 1) {
      return get(splitOnDot[0], splitOnDot[1]);
    }

    for (FieldRef fieldRef : fieldRefs) {
      if (fieldRef.getColumnName().equals(name)) {
        return fieldRef;
      }
    }

    return null;
  }

  public FieldRef get(String tableName, String fieldName) {
    for (FieldRef fieldRef : fieldRefs) {
      if (fieldRef.getColumnName().equals(fieldName) &&
          (fieldRef.getTableName().equals(tableName) ||
              (fieldRef.getTableAlias() != null &&
                  fieldRef.getTableAlias().equals(tableName)))) {
        return fieldRef;
      }
    }

    return null;
  }

  public List<FieldRef> getAllForTable(String tableName) {
    List<FieldRef> resultRefs = new ArrayList<>();

    for (FieldRef fieldRef : fieldRefs) {
      if (fieldRef.getTableName().equals(tableName)) {
        resultRefs.add(fieldRef);
      }
    }

    return resultRefs;
  }
}
