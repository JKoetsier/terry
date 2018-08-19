package nl.jkoetsier.terry.connector.mongodb.workload;

import nl.jkoetsier.terry.intrep.workload.expression.operator.AndOp;
import nl.jkoetsier.terry.intrep.workload.expression.operator.DivideOp;
import nl.jkoetsier.terry.intrep.workload.expression.operator.EqualsOp;
import nl.jkoetsier.terry.intrep.workload.expression.operator.GtOp;
import nl.jkoetsier.terry.intrep.workload.expression.operator.GteOp;
import nl.jkoetsier.terry.intrep.workload.expression.operator.LtOp;
import nl.jkoetsier.terry.intrep.workload.expression.operator.LteOp;
import nl.jkoetsier.terry.intrep.workload.expression.operator.MinusOp;
import nl.jkoetsier.terry.intrep.workload.expression.operator.ModuloOp;
import nl.jkoetsier.terry.intrep.workload.expression.operator.MultiplyOp;
import nl.jkoetsier.terry.intrep.workload.expression.operator.NeqOp;
import nl.jkoetsier.terry.intrep.workload.expression.operator.OrOp;
import nl.jkoetsier.terry.intrep.workload.expression.operator.PlusOp;
import nl.jkoetsier.terry.intrep.workload.visitor.OperatorVisitorAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperatorVisitor extends OperatorVisitorAdapter {

  private static Logger logger = LoggerFactory.getLogger(OperatorVisitor.class);

  private String operator;

  public String getOperator() {
    return operator;
  }

  @Override
  public void visit(AndOp andOp) {
    operator = "$and";
  }

  @Override
  public void visit(DivideOp divideOp) {
    operator = "/";
  }

  @Override
  public void visit(EqualsOp equalsOp) {
  }

  @Override
  public void visit(GteOp gteOp) {
    operator = "$gte";
  }

  @Override
  public void visit(GtOp gtOp) {
    operator = "$gt";
  }

  @Override
  public void visit(LteOp lteOp) {
    operator = "$lte";
  }

  @Override
  public void visit(LtOp ltOp) {
    operator = "$lt";
  }

  @Override
  public void visit(MinusOp minusOp) {
    operator = "-";
  }

  @Override
  public void visit(ModuloOp moduloOp) {
    operator = "%";
  }

  @Override
  public void visit(MultiplyOp multiplyOp) {
    operator = "*";
  }

  @Override
  public void visit(NeqOp neqOp) {
    operator = "$ne";
  }

  @Override
  public void visit(OrOp orOp) {
    operator = "$or";
  }

  @Override
  public void visit(PlusOp plusOp) {
    operator = "+";
  }
}
