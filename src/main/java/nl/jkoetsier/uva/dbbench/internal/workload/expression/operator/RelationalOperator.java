package nl.jkoetsier.uva.dbbench.internal.workload.expression.operator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RelationalOperator extends Operator {

  private static Logger logger = LoggerFactory.getLogger(RelationalOperator.class);

  @Override
  public boolean isArithmetic() {
    return false;
  }

  @Override
  public boolean isRelational() {
    return true;
  }

  @Override
  public boolean isLogical() {
    return false;
  }
}
