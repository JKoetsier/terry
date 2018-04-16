package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.workload.expression.Expression;

public class OuterJoin extends RAJoin {

    public enum Direction {
        LEFT,
        RIGHT
    }

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
}
