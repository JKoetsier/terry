package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.schema.Entity;
import nl.jkoetsier.uva.dbbench.schema.fields.Field;

public class InputRelation extends Relation {

    private Entity entity;

    public InputRelation(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public Field getField(String s) {
        return entity.getField(s);
    }
}
