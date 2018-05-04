package nl.jkoetsier.uva.dbbench.internal.schema;

import java.util.HashMap;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.internal.schema.visitor.SchemaTreeElement;
import nl.jkoetsier.uva.dbbench.internal.schema.visitor.SchemaVisitor;

public class Schema implements SchemaTreeElement {

  private HashMap<String, Entity> entities;

  public Schema() {
    entities = new HashMap<>();
  }

  public HashMap<String, Entity> getEntities() {
    return entities;
  }

  public void setEntities(HashMap<String, Entity> entities) {
    this.entities = entities;
  }

  public void addEntity(Entity entity) {
    this.entities.put(entity.getName(), entity);
  }

  public Entity getEntity(String name) {
    return this.entities.get(name);
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {

    for (Entry<String, Entity> entityEntry : entities.entrySet()) {
      entityEntry.getValue().acceptVisitor(v);
    }
    v.visit(this);
  }
}