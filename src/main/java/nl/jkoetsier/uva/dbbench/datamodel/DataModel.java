package nl.jkoetsier.uva.dbbench.datamodel;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

    private List<Entity> entities;

    public DataModel() {
        entities = new ArrayList<>();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
