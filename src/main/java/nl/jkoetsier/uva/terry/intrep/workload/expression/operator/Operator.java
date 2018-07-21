package nl.jkoetsier.uva.terry.intrep.workload.expression.operator;

import nl.jkoetsier.uva.terry.intrep.workload.visitor.WorkloadElement;

public abstract class Operator implements WorkloadElement {

  public abstract boolean isArithmetic();
  public abstract boolean isRelational();
  public abstract boolean isLogical();
}
