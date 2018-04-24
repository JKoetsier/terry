package nl.jkoetsier.uva.dbbench.internal.workload;

import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadElement;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class Workload implements WorkloadElement {

  private List<Query> queries;
  private boolean isValidated = false;

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
    for (Query query : queries) {
      query.acceptVisitor(workloadVisitor);
    }

    workloadVisitor.visit(this);
  }

  public void validate(Schema schema) throws NotMatchingWorkloadException {
    for (Query query : queries) {
      query.validate(schema);
    }

    isValidated = true;
  }

  public boolean isValidated() {
    return isValidated;
  }
}
