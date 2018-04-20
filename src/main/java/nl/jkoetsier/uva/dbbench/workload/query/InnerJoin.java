package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.workload.expression.Expression;
import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadVisitor;

public class InnerJoin extends RAJoin {

  public InnerJoin(Relation leftInput, Relation rightInput, Expression onExpression) {
    super(leftInput, rightInput, onExpression);
  }

  public InnerJoin(Relation leftInput, Relation rightInput) {
    super(leftInput, rightInput);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
