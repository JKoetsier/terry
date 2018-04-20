package nl.jkoetsier.uva.dbbench.workload;

import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadTreeElement;
import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadVisitor;

public class Workload implements WorkloadTreeElement {

  private List<Query> queries;

  public Workload() {
    queries = new ArrayList<>();
  }

  public List<Query> getQueries() {
    return queries;
  }

  public void setQueries(List<Query> queries) {
    this.queries = queries;
  }

  public void addQuery(Query query) {
    queries.add(query);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
