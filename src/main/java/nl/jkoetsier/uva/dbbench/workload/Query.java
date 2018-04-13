package nl.jkoetsier.uva.dbbench.workload;

import nl.jkoetsier.uva.dbbench.workload.query.Relation;

public class Query {

    private Relation relation;

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }
}
