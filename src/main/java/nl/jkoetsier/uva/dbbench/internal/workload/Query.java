package nl.jkoetsier.uva.dbbench.internal.workload;

import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadElement;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class Query implements WorkloadElement {

  private Relation relation;
  private boolean isValidated = false;

  public Relation getRelation() {
    return relation;
  }

  public void setRelation(Relation relation) {
    this.relation = relation;
  }

  public Query(Relation relation) {
    this.relation = relation;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    relation.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }

  public void validate(Schema schema) throws NotMatchingWorkloadException {
    relation.validate(schema);
    isValidated = true;
  }

  public boolean isValidated() {
    return isValidated;
  }
}
