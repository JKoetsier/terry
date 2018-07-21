package nl.jkoetsier.uva.terry.bench.querystripper;

import java.util.ArrayList;
import java.util.List;
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
import nl.jkoetsier.uva.terry.intrep.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryStripVisitor extends WorkloadVisitor {

  private static Logger logger = LoggerFactory.getLogger(QueryStripVisitor.class);

  private List<Query> queries = new ArrayList<>();

  public List<Query> getQueries() {
    return queries;
  }

  @Override
  public void visit(DateConstant dateConstant) {

  }

  @Override
  public void visit(DoubleConstant doubleConstant) {

  }

  @Override
  public void visit(LongConstant longConstant) {

  }

  @Override
  public void visit(NullValue nullConstant) {

  }

  @Override
  public void visit(StringConstant stringConstant) {

  }

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
  public void visit(BinExpression binExpression) {

  }

  @Override
  public void visit(FieldExpression fieldExpression) {

  }

  @Override
  public void visit(FullJoin fullJoin) {

  }

  @Override
  public void visit(InnerJoin innerJoin) {

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
  public void visit(FunctionExpr functionExpr) {

  }

  @Override
  public void visit(ExpressionList expressionList) {

  }

  @Override
  public void visit(IsNullExpr isNullExpr) {

  }

  @Override
  public void visit(Rename rename) {

  }

  @Override
  public void visit(SelectExpression selectExpression) {

  }

  @Override
  public void visit(Cast cast) {

  }

  @Override
  public void visit(Case caseExpr) {

  }

  @Override
  public void visit(RelationExpression relationExpression) {
    Query query = new Query(relationExpression.getRelation());
    queries.add(query);
  }

  @Override
  public void visit(InExpression inExpression) {

  }

  @Override
  public void visit(SelectAllColumnsExpression selectAllColumnsExpression) {

  }

  @Override
  public void visit(DateExpression dateExpression) {

  }

  @Override
  public void visit(IntervalExpression intervalExpression) {

  }

  @Override
  public void visit(SimpleJoin simpleJoin) {

  }

  @Override
  public void visit(BetweenExpression betweenExpression) {

  }

  @Override
  public void visit(ExistsExpression existsExpression) {

  }

  @Override
  public void visit(LikeExpression likeExpression) {

  }

  @Override
  public void visit(ExtractExpression extractExpression) {

  }

  @Override
  public void visit(StarExpression starExpression) {

  }
}
