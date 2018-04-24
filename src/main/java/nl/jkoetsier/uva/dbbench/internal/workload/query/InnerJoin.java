package nl.jkoetsier.uva.dbbench.internal.workload.query;

import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Expression;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class InnerJoin extends RAJoin {

  public InnerJoin(Relation leftInput, Relation rightInput, Expression onExpression) {
    super(leftInput, rightInput, onExpression);
  }

  public InnerJoin(Relation leftInput, Relation rightInput) {
    super(leftInput, rightInput);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    rightInput.acceptVisitor(workloadVisitor);

    if (onExpression != null) {
      onExpression.acceptVisitor(workloadVisitor);
    }

    workloadVisitor.visit(this);
  }

  @Override
  public void validate(Schema schema) throws NotMatchingWorkloadException {
    leftInput.validate(schema);
    rightInput.validate(schema);

    if (onExpression != null) {
      onExpression.validate(schema, this);
    }

    isValidated = true;
  }
}
