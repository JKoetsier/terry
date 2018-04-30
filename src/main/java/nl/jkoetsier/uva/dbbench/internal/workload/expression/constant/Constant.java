package nl.jkoetsier.uva.dbbench.internal.workload.expression.constant;

import nl.jkoetsier.uva.dbbench.internal.workload.expression.Expression;
import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadElement;

public abstract class Constant extends Expression implements WorkloadElement {

  @Override
  public void validate(ExposedFields exposedFields) {

  }
}
