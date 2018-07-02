package nl.jkoetsier.uva.terry.internal.workload.expression.operator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ArithmeticOperator extends Operator {

  private static Logger logger = LoggerFactory.getLogger(ArithmeticOperator.class);

  @Override
  public boolean isArithmetic() {
    return true;
  }

  @Override
  public boolean isRelational() {
    return false;
  }

  @Override
  public boolean isLogical() {
    return false;
  }
}
