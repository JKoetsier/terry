package nl.jkoetsier.uva.terry.intrep.workload;

import nl.jkoetsier.uva.terry.intrep.QueryResult;
import nl.jkoetsier.uva.terry.intrep.workload.query.Relation;
import nl.jkoetsier.uva.terry.intrep.workload.visitor.WorkloadElement;
import nl.jkoetsier.uva.terry.intrep.workload.visitor.WorkloadVisitor;

public class Query implements WorkloadElement {

  private String identifier;
  private Relation relation;
  private QueryResult expectedResult;

  public Query(Relation relation) {
    this.relation = relation;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public Relation getRelation() {
    return relation;
  }

  public void setRelation(Relation relation) {
    this.relation = relation;
  }

  public QueryResult getExpectedResult() {
    return expectedResult;
  }

  public void setExpectedResult(QueryResult expectedResult) {
    this.expectedResult = expectedResult;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    relation.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "Query{" +
        "identifier=" + identifier +
        ", relation=" + relation +
        '}';
  }
}
