package nl.jkoetsier.uva.terry.internal.workload.expression.constant;

import nl.jkoetsier.uva.terry.internal.workload.expression.Expression;
import nl.jkoetsier.uva.terry.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.terry.internal.workload.visitor.WorkloadElement;

public abstract class Constant extends Expression implements WorkloadElement {

  public abstract Object getValue();

  @Override
  public void validate(ExposedFields exposedFields) {

  }
}
