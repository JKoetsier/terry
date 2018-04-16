package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.schema.fields.Field;

public class FieldRef {

  private Field field;
  private String tableName;
  private String tableAlias;
  private String columnName;
  private String columnAlias;

  public FieldRef() {
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
}
