package nl.jkoetsier.uva.dbbench.internal.schema;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.Column;
import nl.jkoetsier.uva.dbbench.internal.schema.visitor.SchemaTreeElement;
import nl.jkoetsier.uva.dbbench.internal.schema.visitor.SchemaVisitor;

public class Table implements SchemaTreeElement {

  private LinkedHashMap<String, Column> columns;
  private String name;
  private List<Column> primaryKey;

  public Table(String name) {
    this.name = name;
    columns = new LinkedHashMap<>();
  }

  public LinkedHashMap<String, Column> getColumns() {
    return columns;
  }

  public void setColumns(LinkedHashMap<String, Column> columns) {
    this.columns = columns;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void addColumn(Column column) {
    columns.put(column.getName(), column);
  }

  public Column getColumn(String name) {
    return columns.get(name);
  }

  public List<Column> getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(List<Column> primaryKey) {
    this.primaryKey = primaryKey;
  }

  public List<String> getPrimaryKeyFieldNames() {
    List<String> keyColumns = new ArrayList<>();

    if (primaryKey == null) {
      return keyColumns;
    }

    for (Column column : primaryKey) {
      keyColumns.add(column.getName());
    }

    return keyColumns;
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {

    for (Entry<String, Column> fieldEntry : columns.entrySet()) {
      fieldEntry.getValue().acceptVisitor(v);
    }

    v.visit(this);
  }
}
