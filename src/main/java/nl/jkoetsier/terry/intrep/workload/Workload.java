package nl.jkoetsier.terry.intrep.workload;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadElement;
import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;
import nl.jkoetsier.terry.intrep.schema.Schema;
import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadValidationVisitor;

public class Workload implements WorkloadElement {

  private HashMap<String, Query> queries;

  public Workload() {
    queries = new HashMap<>();
  }

  public Workload(List<Query> queryList) {
    this();

    for (Query query : queryList) {
      addQuery(query);
    }
  }

  public HashMap<String, Query> getQueries() {
    return queries;
  }

  public void addQuery(Query query) {
    if (query.getIdentifier() == null) {
      query.setIdentifier(Integer.toString(queries.size()));
    }

    queries.put(query.getIdentifier(), query);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    for (Entry<String, Query> entry : queries.entrySet()) {
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

  public Query getQuery(String key) {
    return queries.get(key);
  }

  public Query getQuery(int key) {
    return queries.get(Integer.toString(key));
  }
}
