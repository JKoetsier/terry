package nl.jkoetsier.terry.intrep.workload.expression.constant;

import nl.jkoetsier.terry.intrep.workload.expression.Expression;
import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadElement;

public abstract class Constant extends Expression implements WorkloadElement {

  public abstract Object getValue();
}
