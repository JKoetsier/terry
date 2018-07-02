package nl.jkoetsier.uva.terry.internal.workload.visitor;

import nl.jkoetsier.uva.terry.internal.workload.Query;
import nl.jkoetsier.uva.terry.internal.workload.Workload;
import nl.jkoetsier.uva.terry.internal.workload.expression.operator.AndOp;
import nl.jkoetsier.uva.terry.internal.workload.expression.operator.DivideOp;
import nl.jkoetsier.uva.terry.internal.workload.expression.operator.EqualsOp;
import nl.jkoetsier.uva.terry.internal.workload.expression.operator.GtOp;
import nl.jkoetsier.uva.terry.internal.workload.expression.operator.GteOp;
import nl.jkoetsier.uva.terry.internal.workload.expression.operator.LtOp;
import nl.jkoetsier.uva.terry.internal.workload.expression.operator.LteOp;
import nl.jkoetsier.uva.terry.internal.workload.expression.operator.MinusOp;
import nl.jkoetsier.uva.terry.internal.workload.expression.operator.ModuloOp;
import nl.jkoetsier.uva.terry.internal.workload.expression.operator.MultiplyOp;
import nl.jkoetsier.uva.terry.internal.workload.expression.operator.NeqOp;
import nl.jkoetsier.uva.terry.internal.workload.expression.operator.OrOp;
import nl.jkoetsier.uva.terry.internal.workload.expression.operator.PlusOp;
import nl.jkoetsier.uva.terry.internal.workload.query.FullJoin;
import nl.jkoetsier.uva.terry.internal.workload.query.InnerJoin;
import nl.jkoetsier.uva.terry.internal.workload.query.InputRelation;
import nl.jkoetsier.uva.terry.internal.workload.query.OuterJoin;
import nl.jkoetsier.uva.terry.internal.workload.query.Projection;
import nl.jkoetsier.uva.terry.internal.workload.query.Rename;
import nl.jkoetsier.uva.terry.internal.workload.query.Selection;
import nl.jkoetsier.uva.terry.internal.workload.query.SimpleJoin;
import nl.jkoetsier.uva.terry.internal.workload.query.Union;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ExpressionVisitorAdapter extends WorkloadVisitor {

  private static Logger logger = LoggerFactory.getLogger(ExpressionVisitorAdapter.class);

  @Override
  public void visit(AndOp andOp) {

  }

  @Override
  public void visit(DivideOp divideOp) {

  }

  @Override
  public void visit(EqualsOp equalsOp) {

  }

  @Override
  public void visit(GteOp gteOp) {

  }

  @Override
  public void visit(GtOp gtOp) {

  }

  @Override
  public void visit(LteOp lteOp) {

  }

  @Override
  public void visit(LtOp ltOp) {

  }

  @Override
  public void visit(MinusOp minusOp) {

  }

  @Override
  public void visit(ModuloOp moduloOp) {

  }

  @Override
  public void visit(MultiplyOp multiplyOp) {

  }

  @Override
  public void visit(NeqOp neqOp) {

  }

  @Override
  public void visit(OrOp orOp) {

  }

  @Override
  public void visit(PlusOp plusOp) {

  }

  @Override
  public void visit(FullJoin fullJoin) {

  }

  @Override
  public void visit(InnerJoin innerJoin) {

  }

  @Override
  public void visit(SimpleJoin simpleJoin) {

  }

  @Override
  public void visit(InputRelation inputRelation) {

  }

  @Override
  public void visit(OuterJoin outerJoin) {

  }

  @Override
  public void visit(Projection projection) {

  }

  @Override
  public void visit(Selection selection) {

  }

  @Override
  public void visit(Union union) {

  }

  @Override
  public void visit(Query query) {

  }

  @Override
  public void visit(Workload workload) {

  }

  @Override
  public void visit(Rename rename) {

  }
}
