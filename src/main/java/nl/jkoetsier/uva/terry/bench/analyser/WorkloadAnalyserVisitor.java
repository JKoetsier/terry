package nl.jkoetsier.uva.terry.bench.analyser;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import nl.jkoetsier.uva.terry.internal.workload.Query;
import nl.jkoetsier.uva.terry.internal.workload.Workload;
import nl.jkoetsier.uva.terry.internal.workload.element.OrderBy;
import nl.jkoetsier.uva.terry.internal.workload.expression.BetweenExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.BinExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.Case;
import nl.jkoetsier.uva.terry.internal.workload.expression.Cast;
import nl.jkoetsier.uva.terry.internal.workload.expression.DateExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.ExistsExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.Expression;
import nl.jkoetsier.uva.terry.internal.workload.expression.ExpressionList;
import nl.jkoetsier.uva.terry.internal.workload.expression.ExtractExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.FieldExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.FunctionExpr;
import nl.jkoetsier.uva.terry.internal.workload.expression.InExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.IntervalExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.IsNullExpr;
import nl.jkoetsier.uva.terry.internal.workload.expression.LikeExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.NullValue;
import nl.jkoetsier.uva.terry.internal.workload.expression.RelationExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.SelectAllColumnsExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.SelectExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.StarExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.constant.DateConstant;
import nl.jkoetsier.uva.terry.internal.workload.expression.constant.DoubleConstant;
import nl.jkoetsier.uva.terry.internal.workload.expression.constant.LongConstant;
import nl.jkoetsier.uva.terry.internal.workload.expression.constant.StringConstant;
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
import nl.jkoetsier.uva.terry.internal.workload.visitor.WorkloadElement;
import nl.jkoetsier.uva.terry.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkloadAnalyserVisitor extends WorkloadVisitor {

  private static Logger logger = LoggerFactory.getLogger(WorkloadAnalyserVisitor.class);

  private HashMap<String, Integer> columnCounts = new HashMap<>();

  public HashMap<String, Integer> getColumnCounts() {
    return columnCounts;
  }

  private void collectFieldExpressions(WorkloadElement workloadElement) {
    FieldExpressionCollectorVisitor visitor = new FieldExpressionCollectorVisitor();
    workloadElement.acceptVisitor(visitor);

    HashMap<String, Integer> fields = visitor.getColumnCounts();

    for (Entry<String, Integer> entry : fields.entrySet()) {
      if (columnCounts.get(entry.getKey()) != null) {
        columnCounts.put(entry.getKey(), columnCounts.get(entry.getKey()) + entry.getValue());
      } else {
        columnCounts.put(entry.getKey(), entry.getValue());
      }
    }
  }

  private void addField(String fieldName) {
    if (columnCounts.get(fieldName) != null) {
      columnCounts.put(fieldName, columnCounts.get(fieldName) + 1);
    } else {
      columnCounts.put(fieldName, 1);
    }
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
    collectFieldExpressions(fullJoin);
  }

  @Override
  public void visit(InnerJoin innerJoin) {
    collectFieldExpressions(innerJoin);
  }

  @Override
  public void visit(InputRelation inputRelation) {

  }

  @Override
  public void visit(OuterJoin outerJoin) {
    collectFieldExpressions(outerJoin);
  }

  @Override
  public void visit(Projection projection) {
    if (projection.getOrderBy() != null) {
      for (OrderBy orderBy : projection.getOrderBy()) {
        collectFieldExpressions(orderBy.getFieldExpression());
      }
    }

    if (projection.getGroupBy() != null) {
      for (Expression expr : projection.getGroupBy()) {
        collectFieldExpressions(expr);
      }
    }
  }

  @Override
  public void visit(Selection selection) {
    if (selection.getWhereExpression() != null) {
      collectFieldExpressions(selection.getWhereExpression());
    }
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

  }

  @Override
  public void visit(InExpression inExpression) {

  }

  @Override
  public void visit(SelectAllColumnsExpression selectAllColumnsExpression) {

  }

  @Override
  public void visit(StarExpression starExpression) {

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
}
