package nl.jkoetsier.uva.dbbench.workload;

import nl.jkoetsier.uva.dbbench.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadTreeElement;
import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadVisitor;

public class Query implements WorkloadTreeElement {

  private Relation relation;

  public Relation getRelation() {
    return relation;
  }

  public void setRelation(Relation relation) {
    this.relation = relation;
  }


  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    relation.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }
}
