package nl.jkoetsier.uva.terry.internal.workload.expression;

import nl.jkoetsier.uva.terry.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.terry.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtractExpression extends Expression {

  private static Logger logger = LoggerFactory.getLogger(ExtractExpression.class);

  private String what;
  private Expression from;

  public ExtractExpression(String what,
      Expression from) {
    this.what = what;
    this.from = from;
  }

  public String getWhat() {
    return what;
  }

  public void setWhat(String what) {
    this.what = what;
  }

  public Expression getFrom() {
    return from;
  }

  public void setFrom(Expression from) {
    this.from = from;
  }

  @Override
  public void validate(ExposedFields exposedFields) {

  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    from.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }
}
