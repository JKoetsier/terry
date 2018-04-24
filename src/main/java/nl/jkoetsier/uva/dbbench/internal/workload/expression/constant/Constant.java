package nl.jkoetsier.uva.dbbench.internal.workload.expression.constant;

import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Expression;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadElement;

public abstract class Constant extends Expression implements WorkloadElement {

  @Override
  public void validate(Schema schema, Relation relation) throws NotMatchingWorkloadException {
    isValidated = true;
  }

}
