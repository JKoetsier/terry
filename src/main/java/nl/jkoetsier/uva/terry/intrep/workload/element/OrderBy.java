package nl.jkoetsier.uva.terry.intrep.workload.element;

import nl.jkoetsier.uva.terry.intrep.workload.expression.ColumnNameExpression;

public class OrderBy {

  private ColumnNameExpression columnNameExpression;
  private Direction direction;

  public OrderBy(ColumnNameExpression columnNameExpression, Direction direction) {
    this.columnNameExpression = columnNameExpression;
    this.direction = direction;
  }

  public ColumnNameExpression getColumnNameExpression() {
    return columnNameExpression;
  }

  public Direction getDirection() {
    return direction;
  }

  @Override
  public String toString() {
    return "OrderBy{" +
        "columnNameExpression=" + columnNameExpression +
        ", direction=" + direction +
        '}';
  }

  public enum Direction {
    ASC,
    DESC
  }
}
