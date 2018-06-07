package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RelationExpression extends Expression {

  private static Logger logger = LoggerFactory.getLogger(RelationExpression.class);
  private Relation relation;

  public RelationExpression(Relation relation) {
    this.relation = relation;
  }

  public Relation getRelation() {
    return relation;
  }

  public void setRelation(Relation relation) {
    this.relation = relation;
  }

  @Override
  public void validate(ExposedFields exposedFields) {

  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    relation.acceptVisitor(workloadVisitor);
    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "RelationExpression{" +
        "relation=" + relation +
        '}';
  }
}
