package nl.jkoetsier.uva.dbbench.internal.workload.expression.operator;

import nl.jkoetsier.uva.dbbench.internal.workload.visitor.OperatorVisitorAdapter;
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
