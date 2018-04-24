package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadElement;

public abstract class Expression implements WorkloadElement {

  protected boolean isValidated = false;

  public abstract void validate(Schema schema, Relation relation) throws NotMatchingWorkloadException;

  public boolean isValidated() {
    return isValidated;
  }
}
