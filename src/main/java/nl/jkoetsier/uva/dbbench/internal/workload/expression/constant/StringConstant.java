package nl.jkoetsier.uva.dbbench.internal.workload.expression.constant;

import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class StringConstant extends Constant {

  private String value;

  public StringConstant(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
