package nl.jkoetsier.terry.intrep.workload.expression.operator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LogicalOperator extends Operator {

  private static Logger logger = LoggerFactory.getLogger(LogicalOperator.class);

  @Override
  public boolean isArithmetic() {
    return false;
  }

  @Override
  public boolean isRelational() {
    return false;
  }

  @Override
  public boolean isLogical() {
    return true;
  }
}
