package nl.jkoetsier.uva.terry.internal.schema;

import java.util.HashMap;
import java.util.Map.Entry;
import nl.jkoetsier.uva.terry.internal.schema.visitor.SchemaTreeElement;
import nl.jkoetsier.uva.terry.internal.schema.visitor.SchemaVisitor;

public class Schema implements SchemaTreeElement {

  private HashMap<String, Table> tables;

  public Schema() {
    tables = new HashMap<>();
  }

  public HashMap<String, Table> getTables() {
    return tables;
  }

  public void setTables(HashMap<String, Table> tables) {
    this.tables = tables;
  }

  public void addTable(Table table) {
    this.tables.put(table.getName(), table);
  }

  public Table getTable(String name) {
    for (Entry<String, Table> entry : tables.entrySet()) {
      if (entry.getKey().toLowerCase().equals(name.toLowerCase())) {
        return entry.getValue();
      }
    }

    return null;
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {

    for (Entry<String, Table> entityEntry : tables.entrySet()) {
      entityEntry.getValue().acceptVisitor(v);
    }
    v.visit(this);
  }
}