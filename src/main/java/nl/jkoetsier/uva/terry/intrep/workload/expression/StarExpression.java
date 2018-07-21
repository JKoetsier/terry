package nl.jkoetsier.uva.terry.intrep.workload.expression;

import nl.jkoetsier.uva.terry.intrep.workload.query.ExposedFields;
import nl.jkoetsier.uva.terry.intrep.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StarExpression extends Expression {

  private static Logger logger = LoggerFactory.getLogger(StarExpression.class);

  @Override
  public void validate(ExposedFields exposedFields) {

  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);

  }
}
