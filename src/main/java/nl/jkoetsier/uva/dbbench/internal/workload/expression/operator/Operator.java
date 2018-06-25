package nl.jkoetsier.uva.dbbench.internal.workload.expression.operator;

import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadElement;

public abstract class Operator implements WorkloadElement {

  public abstract boolean isArithmetic();
  public abstract boolean isRelational();
  public abstract boolean isLogical();
}
