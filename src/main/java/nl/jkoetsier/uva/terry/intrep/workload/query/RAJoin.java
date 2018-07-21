package nl.jkoetsier.uva.terry.intrep.workload.query;

import nl.jkoetsier.uva.terry.intrep.workload.expression.Expression;

public abstract class RAJoin extends BinaryRelation {

  protected Expression onExpression;

  public RAJoin(Relation leftInput, Relation rightInput, Expression onExpression) {
    super(leftInput, rightInput);
    this.onExpression = onExpression;
  }

  public RAJoin(Relation leftInput, Relation rightInput) {
    super(leftInput, rightInput);
  }

  public Expression getOnExpression() {
    return onExpression;
  }

  public void setOnExpression(Expression onExpression) {
    this.onExpression = onExpression;
  }

  @Override
  public ExposedFields getExposedFields() {
    if (exposedFields == null) {
      exposedFields = leftInput.getExposedFields().merge(rightInput.getExposedFields());
    }
    return exposedFields;
  }
}
