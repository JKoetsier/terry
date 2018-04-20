package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.workload.expression.Expression;
import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadVisitor;

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
    workloadVisitor.visit(this);
  }

  public enum Direction {
    LEFT,
    RIGHT
  }
}
