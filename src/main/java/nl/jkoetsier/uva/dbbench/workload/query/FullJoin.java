package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.workload.expression.Expression;

public class FullJoin extends RAJoin {

  public FullJoin(Relation leftInput, Relation rightInput, Expression onExpression) {
    super(leftInput, rightInput, onExpression);
  }

  public FullJoin(Relation leftInput, Relation rightInput) {
    super(leftInput, rightInput);
  }
}
