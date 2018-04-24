package nl.jkoetsier.uva.dbbench.internal.workload.expression.constant;

import java.util.Date;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class DateConstant extends Constant {

  private Date value;

  public DateConstant(Date value) {
    this.value = value;
  }

  public Date getValue() {
    return value;
  }

  public void setValue(Date value) {
    this.value = value;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }


  @Override
  public boolean isValidated() {
    return isValidated;
  }
}
