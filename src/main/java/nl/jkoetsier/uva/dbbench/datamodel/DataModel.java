package nl.jkoetsier.uva.dbbench.datamodel;

import java.util.HashMap;

public class DataModel {

    private HashMap<String, Entity> entities;

    public DataModel() {
        entities = new HashMap<>();
    }

    public HashMap<String, Entity> getEntities() {
        return entities;
    }

    public void setEntities(HashMap<String, Entity> entities) {
        this.entities = entities;
    }
}
