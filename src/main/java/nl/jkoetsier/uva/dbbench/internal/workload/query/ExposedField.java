package nl.jkoetsier.uva.dbbench.internal.workload.query;

import nl.jkoetsier.uva.dbbench.internal.schema.fields.Column;

public class ExposedField {

  private Column column;
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

  public ExposedField(Column column, String tableName, String columnName) {
    this.tableName = tableName;
    this.columnName = columnName;
    this.column = column;
  }

  public ExposedField(Column column, String tableName, String columnName, String tableAlias) {
    this.tableName = tableName;
    this.columnName = columnName;
    this.column = column;
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

  public void setTableAlias(String tableAlias) {
    this.tableAlias = tableAlias;
  }

  public Column getColumn() {
    return column;
  }

  public void setColumn(Column column) {
    this.column = column;
  }

  public String toString() {

    String columnPart;

    String tablePart = null;

    if (tableAlias != null) {
      tablePart = tableAlias;
    } else if (tableName != null) {
      tablePart = tableName;
    }

    if (tablePart == null) {
      columnPart = String.format("%s", columnName);
    } else {
      columnPart = String.format("%s.%s", tablePart, columnName);
    }

    return String.format("%s", columnPart);
  }

  @Override
  protected ExposedField clone() {
    return new ExposedField(
        this.column,
        this.tableName,
        this.columnName,
        this.tableAlias
    );
  }
}
