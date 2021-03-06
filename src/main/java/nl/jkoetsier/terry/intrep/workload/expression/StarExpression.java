package nl.jkoetsier.terry.intrep.workload.expression;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StarExpression extends Expression {

  private static Logger logger = LoggerFactory.getLogger(StarExpression.class);

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);

  }
}
