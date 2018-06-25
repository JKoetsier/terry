package nl.jkoetsier.uva.dbbench.internal.workload.expression.operator;

import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class ModuloOp extends ArithmeticOperator {

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
