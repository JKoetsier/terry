package nl.jkoetsier.uva.dbbench.internal.workload.query;

import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.uva.dbbench.internal.schema.Entity;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.Field;

public class ExposedFields {

  private List<ExposedField> exposedFields;
//  private boolean isValidated = false;

  public ExposedFields() {
    exposedFields = new ArrayList<>();
  }

  public ExposedFields(List<ExposedField> exposedField) {
    this.exposedFields = exposedField;
  }

  public static ExposedFields create(Entity entity) {
    ExposedFields exposedFields = new ExposedFields();

    for (Field field : entity.getFields().values()) {
      ExposedField exposedField = new ExposedField(field, entity.getName(), field.getName());
      exposedFields.add(exposedField);
    }

    return exposedFields;
  }

  public static ExposedFields create(Entity entity, String tableAlias) {
    ExposedFields exposedFields = new ExposedFields();

    for (Field field : entity.getFields().values()) {
      ExposedField exposedField = new ExposedField(field, entity.getName(), field.getName(),
          tableAlias);
      exposedFields.add(exposedField);
    }

    return exposedFields;
  }

  public void add(ExposedField exposedField) {
    exposedFields.add(exposedField);
  }

  public void addAll(List<ExposedField> exposedFieldList) {
    exposedFields.addAll(exposedFieldList);
  }

  public int size() {
    return exposedFields.size();
  }

  public ExposedField get(String name) {
    String[] splitOnDot = name.split("\\.");

    if (splitOnDot.length > 1) {
      return get(splitOnDot[0], splitOnDot[1]);
    }

    for (ExposedField exposedField : exposedFields) {
      if (exposedField.getColumnName().equals(name)) {
        return exposedField;
      }
    }

    return null;
  }

  public ExposedField get(String tableName, String fieldName) {
    for (ExposedField exposedField : exposedFields) {
      if (exposedField.getColumnName().equals(fieldName) &&
          (exposedField.getTableName().equals(tableName) ||
              (exposedField.getTableAlias() != null &&
                  exposedField.getTableAlias().equals(tableName)))) {
        return exposedField;
      }
    }

    return null;
  }

  public List<ExposedField> getAllForTable(String tableName) {
    List<ExposedField> resultRefs = new ArrayList<>();

    for (ExposedField exposedField : exposedFields) {
      if (exposedField.getTableName().equals(tableName)) {
        resultRefs.add(exposedField);
      }
    }

    return resultRefs;
  }

//  public void validate(Schema schema, Relation relation) throws NotMatchingWorkloadException {
//    for (ExposedField exposedField : exposedFields) {
//      exposedField.validate(schema, relation);
//    }
//
//    isValidated = true;
//  }

  public String toString() {
    List<String> children = new ArrayList<>();

    for (ExposedField exposedField : exposedFields) {
      children.add(exposedField.toString());
    }

    return String.join(", ", children);
  }

  public List<ExposedField> getAll() {
    return exposedFields;
  }

  public ExposedFields merge(ExposedFields exposedFields) {
    ExposedFields merged = new ExposedFields();

    for (ExposedField exposedField : this.exposedFields) {
      merged.add(exposedField);
    }

    for (ExposedField exposedField : exposedFields.getAll()) {
      merged.add(exposedField);
    }

    return merged;
  }

//  public boolean isValidated() {
//    return isValidated;
//  }
}
