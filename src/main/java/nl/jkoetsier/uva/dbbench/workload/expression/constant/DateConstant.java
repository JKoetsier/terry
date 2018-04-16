package nl.jkoetsier.uva.dbbench.workload.expression.constant;

import java.util.Date;

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
}
