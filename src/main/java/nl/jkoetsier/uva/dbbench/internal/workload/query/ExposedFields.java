package nl.jkoetsier.uva.dbbench.internal.workload.query;

import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.uva.dbbench.internal.schema.Table;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.Column;

public class ExposedFields {

  private List<ExposedField> exposedFields;

  public ExposedFields() {
    exposedFields = new ArrayList<>();
  }

  public ExposedFields(List<ExposedField> exposedField) {
    this.exposedFields = exposedField;
  }

  public static ExposedFields create(Table table) {
    ExposedFields exposedFields = new ExposedFields();

    for (Column column : table.getColumns().values()) {
      ExposedField exposedField = new ExposedField(column, table.getName(), column.getName());
      exposedFields.add(exposedField);
    }

    return exposedFields;
  }

  public static ExposedFields create(Table table, String tableAlias) {
    ExposedFields exposedFields = new ExposedFields();

    for (Column column : table.getColumns().values()) {
      ExposedField exposedField = new ExposedField(column, table.getName(), column.getName(),
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
}
