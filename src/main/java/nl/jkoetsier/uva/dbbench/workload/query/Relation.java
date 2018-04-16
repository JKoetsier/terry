package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.schema.fields.Field;

public abstract class Relation {

    public abstract Field getField(String s);

    public boolean producesField(String s) {
        return getField(s) != null;
    }
}
