package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateExpression extends Expression {

  private static Logger logger = LoggerFactory.getLogger(DateExpression.class);

  private String date;

  public DateExpression(String date) {
    this.date = date;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public void validate(ExposedFields exposedFields) {

  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
