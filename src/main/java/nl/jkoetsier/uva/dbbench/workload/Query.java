package nl.jkoetsier.uva.dbbench.workload;

import nl.jkoetsier.uva.dbbench.workload.relalg.Relation;

public class Query {

    private Relation relation;

    public Query(Relation relation) {
        this.relation = relation;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }
}
