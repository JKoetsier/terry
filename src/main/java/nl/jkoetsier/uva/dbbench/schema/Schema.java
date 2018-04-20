package nl.jkoetsier.uva.dbbench.schema;

import java.util.HashMap;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.schema.visitor.SchemaVisitor;
import nl.jkoetsier.uva.dbbench.schema.visitor.TreeElement;

public class Schema implements TreeElement {

  private static Schema instance;
  private HashMap<String, Entity> entities;

  private Schema() {
    entities = new HashMap<>();
  }

  public static Schema getInstance() {
    if (instance == null) {
      instance = new Schema();
    }

    return instance;
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