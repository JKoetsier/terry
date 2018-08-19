package nl.jkoetsier.terry.intrep.workload.expression;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadElement;

public abstract class Expression implements WorkloadElement {

  private boolean not;

  public Expression(boolean not) {
    this.not = not;
  }

  public Expression() {

  }

  public boolean isNot() {
    return not;
  }

  public void setNot(boolean not) {
    this.not = not;
  }
}
