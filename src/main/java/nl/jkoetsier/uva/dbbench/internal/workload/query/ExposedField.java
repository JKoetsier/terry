package nl.jkoetsier.uva.dbbench.internal.workload.query;

import nl.jkoetsier.uva.dbbench.internal.schema.fields.Field;

public class ExposedField {

  private Field field;
  private String tableName;
  private String tableAlias;
  private String columnName;

  public ExposedField(String fullColumnName) {
    String[] splitOnDot = fullColumnName.split("\\.");

    if (splitOnDot.length > 1) {
      tableName = splitOnDot[0];
      columnName = splitOnDot[1];
    } else {
      columnName = splitOnDot[0];
    }
  }

  public ExposedField(Field field, String tableName, String columnName) {
    this.tableName = tableName;
    this.columnName = columnName;
    this.field = field;
  }

  public ExposedField(Field field, String tableName, String columnName, String tableAlias) {
    this.tableName = tableName;
    this.columnName = columnName;
    this.field = field;
    this.tableAlias = tableAlias;
  }

  public void setTableAlias(String tableAlias) {
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

  public String getTableAlias() {
    return tableAlias;
  }

  public Field getField() {
    return field;
  }

  public void setField(Field field) {
    this.field = field;
  }

  public String toString() {

    String columnPart;

    if (tableName == null) {
      columnPart = String.format("%s", columnName);
    } else {
      columnPart = String.format("%s.%s", tableName, columnName);
    }

    return String.format("%s", columnPart);
  }

  @Override
  protected ExposedField clone() {
    return new ExposedField(
        this.field,
        this.tableName,
        this.columnName,
        this.tableAlias
    );
  }
}
