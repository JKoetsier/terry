package nl.jkoetsier.uva.dbbench.internal.workload.query;

import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.Field;

public class FieldRef {

  private Field field;
  private String tableName;
  private String tableAlias;
  private String columnName;
  private String columnAlias;
  private boolean isValidated = false;

  public FieldRef(String fullColumnName) {
    String[] splitOnDot = fullColumnName.split("\\.");

    if (splitOnDot.length > 1) {
      tableName = splitOnDot[0];
      columnName = splitOnDot[1];
    } else {
      columnName = splitOnDot[0];
    }
  }


  public FieldRef(Field field, String tableName, String columnName) {
    this.tableName = tableName;
    this.columnName = columnName;
    this.field = field;
  }

  public FieldRef(Field field, String tableName, String columnName, String tableAlias) {
    this.tableName = tableName;
    this.columnName = columnName;
    this.field = field;
    this.tableAlias = tableAlias;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getColumnAlias() {
    return columnAlias;
  }

  public void setColumnAlias(String columnAlias) {
    this.columnAlias = columnAlias;
  }

  public String getTableAlias() {
    return tableAlias;
  }

  public void setTableAlias(String tableAlias) {
    this.tableAlias = tableAlias;
  }

  public Field getField() {
    return field;
  }

  public void setField(Field field) {
    this.field = field;
  }

  public boolean isValidated() {
    return isValidated;
  }

  public void validate(Schema schema, Relation relation) throws NotMatchingWorkloadException {
    FieldRef existing = relation.getFieldRef(tableName, columnName);
    if (existing == null) {
      throw new NotMatchingWorkloadException(String.format(
          "Field %s.%s does not exist", tableName, columnName
      ));
    }

    field = existing.getField();

    isValidated = true;
  }

  public String toString() {

    String columnPart;

    if (tableName == null) {
      columnPart = String.format("%s", columnName);
    } else {
      columnPart = String.format("%s.%s", tableName, columnName);
    }

    if (columnAlias != null) {
      return String.format("%s AS %s", columnPart, columnAlias);
    } else {
      return String.format("%s", columnPart);
    }
  }
}
