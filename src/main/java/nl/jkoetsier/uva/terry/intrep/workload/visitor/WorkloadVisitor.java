package nl.jkoetsier.uva.terry.intrep.workload.visitor;

import nl.jkoetsier.uva.terry.intrep.workload.Query;
import nl.jkoetsier.uva.terry.intrep.workload.Workload;
import nl.jkoetsier.uva.terry.intrep.workload.expression.BetweenExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.BinExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.Case;
import nl.jkoetsier.uva.terry.intrep.workload.expression.Cast;
import nl.jkoetsier.uva.terry.intrep.workload.expression.DateExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.ExistsExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.ExpressionList;
import nl.jkoetsier.uva.terry.intrep.workload.expression.ExtractExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.FieldExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.FunctionExpr;
import nl.jkoetsier.uva.terry.intrep.workload.expression.InExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.IntervalExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.IsNullExpr;
import nl.jkoetsier.uva.terry.intrep.workload.expression.LikeExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.NullValue;
import nl.jkoetsier.uva.terry.intrep.workload.expression.RelationExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.SelectAllColumnsExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.SelectExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.StarExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.constant.DateConstant;
import nl.jkoetsier.uva.terry.intrep.workload.expression.constant.DoubleConstant;
import nl.jkoetsier.uva.terry.intrep.workload.expression.constant.LongConstant;
import nl.jkoetsier.uva.terry.intrep.workload.expression.constant.StringConstant;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.AndOp;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.DivideOp;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.EqualsOp;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.GtOp;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.GteOp;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.LtOp;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.LteOp;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.MinusOp;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.ModuloOp;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.MultiplyOp;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.NeqOp;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.OrOp;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.PlusOp;
import nl.jkoetsier.uva.terry.intrep.workload.query.FullJoin;
import nl.jkoetsier.uva.terry.intrep.workload.query.InnerJoin;
import nl.jkoetsier.uva.terry.intrep.workload.query.InputRelation;
import nl.jkoetsier.uva.terry.intrep.workload.query.OuterJoin;
import nl.jkoetsier.uva.terry.intrep.workload.query.Projection;
import nl.jkoetsier.uva.terry.intrep.workload.query.Rename;
import nl.jkoetsier.uva.terry.intrep.workload.query.Selection;
import nl.jkoetsier.uva.terry.intrep.workload.query.SimpleJoin;
import nl.jkoetsier.uva.terry.intrep.workload.query.Union;

public abstract class WorkloadVisitor {

  public abstract void visit(DateConstant dateConstant);

  public abstract void visit(DoubleConstant doubleConstant);

  public abstract void visit(LongConstant longConstant);

  public abstract void visit(NullValue nullConstant);

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

  public abstract void visit(Selection selection);

  public abstract void visit(Union union);

  public abstract void visit(Query query);

  public abstract void visit(Workload workload);

  public abstract void visit(FunctionExpr functionExpr);

  public abstract void visit(ExpressionList expressionList);

  public abstract void visit(IsNullExpr isNullExpr);

  public abstract void visit(Rename rename);

  public abstract void visit(SelectExpression selectExpression);

  public abstract void visit(Cast cast);

  public abstract void visit(Case caseExpr);

  public abstract void visit(RelationExpression relationExpression);

  public abstract void visit(InExpression inExpression);

  public abstract void visit(SelectAllColumnsExpression selectAllColumnsExpression);

  public abstract void visit(StarExpression starExpression);

  public abstract void visit(DateExpression dateExpression);

  public abstract void visit(IntervalExpression intervalExpression);

  public abstract void visit(SimpleJoin simpleJoin);

  public abstract void visit(BetweenExpression betweenExpression);

  public abstract void visit(ExistsExpression existsExpression);

  public abstract void visit(LikeExpression likeExpression);

  public abstract void visit(ExtractExpression extractExpression);
}
