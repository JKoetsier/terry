package nl.jkoetsier.uva.dbbench.internal.workload.visitor;

import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;

public interface WorkloadElement {

  void acceptVisitor(WorkloadVisitor workloadVisitor);

}
