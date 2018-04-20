package nl.jkoetsier.uva.dbbench.workload.visitor;

import com.sun.org.apache.xpath.internal.operations.Gt;
import com.sun.org.apache.xpath.internal.operations.Mod;
import nl.jkoetsier.uva.dbbench.workload.Query;
import nl.jkoetsier.uva.dbbench.workload.Workload;
import nl.jkoetsier.uva.dbbench.workload.expression.BinExpression;
import nl.jkoetsier.uva.dbbench.workload.expression.FieldExpression;
import nl.jkoetsier.uva.dbbench.workload.expression.constant.DateConstant;
import nl.jkoetsier.uva.dbbench.workload.expression.constant.DoubleConstant;
import nl.jkoetsier.uva.dbbench.workload.expression.constant.LongConstant;
import nl.jkoetsier.uva.dbbench.workload.expression.constant.StringConstant;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.AndOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.DivideOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.EqualsOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.GtOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.GteOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.LtOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.LteOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.MinusOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.ModuloOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.MultiplyOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.NeqOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.OrOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.PlusOp;
import nl.jkoetsier.uva.dbbench.workload.query.FullJoin;
import nl.jkoetsier.uva.dbbench.workload.query.InnerJoin;
import nl.jkoetsier.uva.dbbench.workload.query.InputRelation;
import nl.jkoetsier.uva.dbbench.workload.query.OuterJoin;
import nl.jkoetsier.uva.dbbench.workload.query.Projection;
import nl.jkoetsier.uva.dbbench.workload.query.Rename;
import nl.jkoetsier.uva.dbbench.workload.query.Selection;

public abstract class WorkloadVisitor {

  public abstract void visit(DateConstant dateConstant);
  public abstract void visit(DoubleConstant doubleConstant);
  public abstract void visit(LongConstant longConstant);
  public abstract void visit(StringConstant stringConstant);

  public abstract void visit(AndOp andOp);
  public abstract void visit(DivideOp divideOp);
  public abstract void visit(EqualsOp equalsOp);
  public abstract void visit(GteOp gteOp);
  public abstract void visit(GtOp gtOp);
  public abstract void visit(LteOp lteOp);
  public abstract void visit(LtOp ltOp);
  public abstract void visit(MinusOp minusOp);
  public abstract void visit(ModuloOp moduloOp);
  public abstract void visit(MultiplyOp multiplyOp);
  public abstract void visit(NeqOp neqOp);
  public abstract void visit(OrOp orOp);
  public abstract void visit(PlusOp plusOp);

  public abstract void visit(BinExpression binExpression);
  public abstract void visit(FieldExpression fieldExpression);

  public abstract void visit(FullJoin fullJoin);
  public abstract void visit(InnerJoin innerJoin);
  public abstract void visit(InputRelation inputRelation);
  public abstract void visit(OuterJoin outerJoin);
  public abstract void visit(Projection projection);
  public abstract void visit(Rename rename);
  public abstract void visit(Selection selection);

  public abstract void visit(Query query);
  public abstract void visit(Workload workload);
}
