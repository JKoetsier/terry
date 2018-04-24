package nl.jkoetsier.uva.dbbench.internal.workload.query;

import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Expression;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class OuterJoin extends RAJoin {

  private Direction direction;

  public enum Direction {
    LEFT,
    RIGHT
  }

  public OuterJoin(Relation leftInput, Relation rightInput, Direction direction, Expression
      onExpression) {
    super(leftInput, rightInput, onExpression);
    this.direction = direction;
  }

  public OuterJoin(Relation leftInput, Relation rightInput, Direction direction) {
    super(leftInput, rightInput);
    this.direction = direction;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    leftInput.acceptVisitor(workloadVisitor);
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
