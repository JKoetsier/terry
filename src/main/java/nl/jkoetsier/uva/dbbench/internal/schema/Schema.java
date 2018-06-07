package nl.jkoetsier.uva.dbbench.internal.schema;

import java.util.HashMap;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.internal.schema.visitor.SchemaTreeElement;
import nl.jkoetsier.uva.dbbench.internal.schema.visitor.SchemaVisitor;

public class Schema implements SchemaTreeElement {

  private HashMap<String, Table> entities;

  public Schema() {
    entities = new HashMap<>();
  }

  public HashMap<String, Table> getEntities() {
    return entities;
  }

  public void setEntities(HashMap<String, Table> entities) {
    this.entities = entities;
  }

  public void addEntity(Table table) {
    this.entities.put(table.getName(), table);
  }

  public Table getEntity(String name) {
    for (Entry<String, Table> entry : entities.entrySet()) {
      if (entry.getKey().toLowerCase().equals(name.toLowerCase())) {
        return entry.getValue();
      }
    }

    return null;
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {

    for (Entry<String, Table> entityEntry : entities.entrySet()) {
      entityEntry.getValue().acceptVisitor(v);
    }
    v.visit(this);
  }
}