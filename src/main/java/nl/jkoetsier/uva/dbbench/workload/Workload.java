package nl.jkoetsier.uva.dbbench.workload;

import java.util.List;

public class Workload {

    private List<Query> queries;

    public Workload(List<Query> queries) {
        this.queries = queries;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }
}
