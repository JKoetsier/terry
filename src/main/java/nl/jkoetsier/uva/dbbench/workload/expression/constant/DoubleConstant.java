package nl.jkoetsier.uva.dbbench.workload.expression.constant;

import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadVisitor;

public class DoubleConstant extends Constant {

  private Double value;

  public DoubleConstant(Double value) {
    this.value = value;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
