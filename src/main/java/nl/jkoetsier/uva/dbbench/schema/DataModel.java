package nl.jkoetsier.uva.dbbench.schema;

import java.util.HashMap;

public class DataModel {

  private static DataModel instance;
  private HashMap<String, Entity> entities;

  private DataModel() {
    entities = new HashMap<>();
  }

  public static DataModel getInstance() {
    if (instance == null) {
      instance = new DataModel();
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
}