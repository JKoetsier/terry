package nl.jkoetsier.uva.dbbench.internal.workload;

import java.util.HashMap;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadElement;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadValidationVisitor;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class Workload implements WorkloadElement {

  private HashMap<Integer, Query> queries;

  public Workload() {
    queries = new HashMap<>();
  }

  public HashMap<Integer, Query> getQueries() {
    return queries;
  }

  public void addQuery(Query query) {
    query.setIdentifier(queries.size());
    queries.put(query.getIdentifier(), query);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    for (Entry<Integer, Query> entry : queries.entrySet()) {
      entry.getValue().acceptVisitor(workloadVisitor);
    }

    workloadVisitor.visit(this);
  }

  public void validate(Schema schema) {
    WorkloadValidationVisitor workloadValidationVisitor = new WorkloadValidationVisitor(schema);

    this.acceptVisitor(workloadValidationVisitor);
  }

  @Override
  public String toString() {
    return "Workload{" +
        "queries=" + queries +
        '}';
  }
}
