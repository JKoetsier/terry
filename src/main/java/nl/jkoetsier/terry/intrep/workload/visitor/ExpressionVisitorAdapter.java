package nl.jkoetsier.terry.intrep.workload.visitor;

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
import nl.jkoetsier.terry.intrep.workload.Query;
import nl.jkoetsier.terry.intrep.workload.Workload;
import nl.jkoetsier.terry.intrep.workload.query.FullJoin;
import nl.jkoetsier.terry.intrep.workload.query.InnerJoin;
import nl.jkoetsier.terry.intrep.workload.query.InputRelation;
import nl.jkoetsier.terry.intrep.workload.query.OuterJoin;
import nl.jkoetsier.terry.intrep.workload.query.Projection;
import nl.jkoetsier.terry.intrep.workload.query.Rename;
import nl.jkoetsier.terry.intrep.workload.query.Selection;
import nl.jkoetsier.terry.intrep.workload.query.SimpleJoin;
import nl.jkoetsier.terry.intrep.workload.query.Union;
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
