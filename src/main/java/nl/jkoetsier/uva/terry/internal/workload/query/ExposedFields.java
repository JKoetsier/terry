package nl.jkoetsier.uva.terry.internal.workload.query;

import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.uva.terry.internal.schema.Table;
import nl.jkoetsier.uva.terry.internal.schema.fields.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExposedFields {

  private static Logger logger = LoggerFactory.getLogger(ExposedFields.class);

  private List<ExposedField> exposedFields;

  public ExposedFields() {
    exposedFields = new ArrayList<>();
  }

  public ExposedFields(List<ExposedField> exposedFields) {
    this.exposedFields = exposedFields;
  }

  public ExposedFields(ExposedField exposedField) {
    this();
    exposedFields.add(exposedField);
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

  public void addAll(ExposedFields exposedFields) {
    for (ExposedField exposedField : exposedFields.getAll()) {
      add(exposedField);
    }
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
      if (exposedField.getColumnName().toLowerCase().equals(name.toLowerCase())) {
        return exposedField;
      }
    }

    return null;
  }

  public ExposedField get(String tableName, String fieldName) {
    for (ExposedField exposedField : exposedFields) {
      if (exposedField.getColumnName().toLowerCase().equals(fieldName.toLowerCase()) &&
          (exposedField.getTableName().toLowerCase().equals(tableName.toLowerCase()) ||
              (exposedField.getTableAlias() != null &&
                  exposedField.getTableAlias().toLowerCase().equals(tableName.toLowerCase())))) {
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

  public void setAlias(String tableAlias) {
    for (ExposedField exposedField : exposedFields) {
      exposedField.setTableAlias(tableAlias);
    }
  }
}
