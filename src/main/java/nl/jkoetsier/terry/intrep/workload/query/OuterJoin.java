package nl.jkoetsier.terry.intrep.workload.query;

import nl.jkoetsier.terry.intrep.workload.expression.Expression;
import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;

public class OuterJoin extends RAJoin {

  private Direction direction;

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
  public String toString() {
    return "OuterJoin{" +
        "direction=" + direction +
        ", onExpression=" + onExpression +
        ", leftInput=" + leftInput +
        ", rightInput=" + rightInput +
        '}';
  }

  public enum Direction {
    LEFT,
    RIGHT
  }
}
