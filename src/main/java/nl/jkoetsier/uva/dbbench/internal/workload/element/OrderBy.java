package nl.jkoetsier.uva.dbbench.internal.workload.element;

import nl.jkoetsier.uva.dbbench.internal.workload.expression.FieldExpression;

public class OrderBy {

  private FieldExpression fieldExpression;
  private Direction direction;

  public OrderBy(FieldExpression fieldExpression, Direction direction) {
    this.fieldExpression = fieldExpression;
    this.direction = direction;
  }

  public FieldExpression getFieldExpression() {
    return fieldExpression;
  }

  public Direction getDirection() {
    return direction;
  }

  public enum Direction {
    ASC,
    DESC
  }
}
