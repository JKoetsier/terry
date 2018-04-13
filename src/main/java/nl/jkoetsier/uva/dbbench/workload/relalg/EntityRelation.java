package nl.jkoetsier.uva.dbbench.workload.relalg;

import nl.jkoetsier.uva.dbbench.datamodel.Entity;

public class EntityRelation extends UnaryRelation {

    private Entity entity;

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
