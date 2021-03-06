package nl.jkoetsier.terry.connector.mongodb.workload;

import com.mongodb.BasicDBObject;
import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.terry.connector.mongodb.MongoDbQuery;
import nl.jkoetsier.terry.intrep.ExecutableQuery;
import nl.jkoetsier.terry.intrep.workload.Query;
import nl.jkoetsier.terry.intrep.workload.Workload;
import nl.jkoetsier.terry.intrep.workload.expression.BetweenExpression;
import nl.jkoetsier.terry.intrep.workload.expression.BinExpression;
import nl.jkoetsier.terry.intrep.workload.expression.Case;
import nl.jkoetsier.terry.intrep.workload.expression.Cast;
import nl.jkoetsier.terry.intrep.workload.expression.ColumnNameExpression;
import nl.jkoetsier.terry.intrep.workload.expression.DateExpression;
import nl.jkoetsier.terry.intrep.workload.expression.ExistsExpression;
import nl.jkoetsier.terry.intrep.workload.expression.ExpressionList;
import nl.jkoetsier.terry.intrep.workload.expression.ExtractExpression;
import nl.jkoetsier.terry.intrep.workload.expression.FunctionExpr;
import nl.jkoetsier.terry.intrep.workload.expression.InExpression;
import nl.jkoetsier.terry.intrep.workload.expression.IntervalExpression;
import nl.jkoetsier.terry.intrep.workload.expression.IsNullExpr;
import nl.jkoetsier.terry.intrep.workload.expression.LikeExpression;
import nl.jkoetsier.terry.intrep.workload.expression.NullValue;
import nl.jkoetsier.terry.intrep.workload.expression.RelationExpression;
import nl.jkoetsier.terry.intrep.workload.expression.SelectAllColumnsExpression;
import nl.jkoetsier.terry.intrep.workload.expression.SelectExpression;
import nl.jkoetsier.terry.intrep.workload.expression.StarExpression;
import nl.jkoetsier.terry.intrep.workload.expression.constant.DateConstant;
import nl.jkoetsier.terry.intrep.workload.expression.constant.DoubleConstant;
import nl.jkoetsier.terry.intrep.workload.expression.constant.LongConstant;
import nl.jkoetsier.terry.intrep.workload.expression.constant.StringConstant;
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
import nl.jkoetsier.terry.intrep.workload.query.FullJoin;
import nl.jkoetsier.terry.intrep.workload.query.InnerJoin;
import nl.jkoetsier.terry.intrep.workload.query.InputRelation;
import nl.jkoetsier.terry.intrep.workload.query.OuterJoin;
import nl.jkoetsier.terry.intrep.workload.query.Projection;
import nl.jkoetsier.terry.intrep.workload.query.Rename;
import nl.jkoetsier.terry.intrep.workload.query.Selection;
import nl.jkoetsier.terry.intrep.workload.query.SimpleJoin;
import nl.jkoetsier.terry.intrep.workload.query.Union;
import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDbWorkloadVisitor extends WorkloadVisitor {

  private static Logger logger = LoggerFactory.getLogger(MongoDbWorkloadVisitor.class);

  private List<ExecutableQuery> result;

  private BasicDBObject projection;
  private BasicDBObject find;
  private String collection;
  private Long limit;
  private Long offset;
  private BasicDBObject sort;

  private boolean supported = true;

  public MongoDbWorkloadVisitor() {
    result = new ArrayList<>();
  }

  public List<ExecutableQuery> getResult() {
    return result;
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
  public void visit(ColumnNameExpression columnNameExpression) {

  }

  @Override
  public void visit(FullJoin fullJoin) {
    supported = false;
  }

  @Override
  public void visit(InnerJoin innerJoin) {
    supported = false;
  }

  @Override
  public void visit(InputRelation inputRelation) {
    collection = inputRelation.getTableName();
  }

  @Override
  public void visit(OuterJoin outerJoin) {
    supported = false;
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
  public void visit(Projection projection) {
    if (projection.getLimit() != null && projection.getLimit() instanceof LongConstant) {
      limit = ((LongConstant) projection.getLimit()).getValue();
    }

    if (projection.getOffset() != null && projection.getOffset() instanceof LongConstant) {
      offset = ((LongConstant)projection.getOffset()).getValue();
    }
  }

  @Override
  public void visit(Selection selection) {
    if (selection.getWhereExpression() != null) {
      WhereExpressionVisitor whereExpressionVisitor = new WhereExpressionVisitor();
      selection.getWhereExpression().acceptVisitor(whereExpressionVisitor);

      find = whereExpressionVisitor.getWhereExpression();
    }
  }

  @Override
  public void visit(Union union) {
    supported = false;
  }

  @Override
  public void visit(Query query) {
    MongoDbQuery mongoDbQuery = new MongoDbQuery(query);

    if (find != null) {
      mongoDbQuery.setFind(find);
      find = null;
    }

    if (projection != null) {
      mongoDbQuery.setProjection(projection);
      projection = null;
    }

    if (collection != null) {
      mongoDbQuery.setCollection(collection);
      collection = null;
    }

    if (limit != null) {
      mongoDbQuery.setLimit(limit);
      limit = null;
    }

    if (offset != null) {
      mongoDbQuery.setOffset(offset);
      offset = null;
    }

    if (sort != null) {
      mongoDbQuery.setSort(sort);
      sort = null;
    }

    mongoDbQuery.setSupported(supported);
    supported = true;

    result.add(mongoDbQuery);
  }

  @Override
  public void visit(Workload workload) {

  }

  @Override
  public void visit(FunctionExpr functionExpr) {
    supported = false;
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
    if (selectExpression.getExpression() instanceof ColumnNameExpression) {
      ColumnNameExpression columnNameExpression = (ColumnNameExpression) selectExpression.getExpression();

      if (projection != null) {
        projection.append(columnNameExpression.getColumnName(), 1);
      } else {
        projection = new BasicDBObject(columnNameExpression.getColumnName(), 1);
      }
    } else if (selectExpression.getExpression() instanceof SelectAllColumnsExpression) {
      // Do nothing
    } else {
      logger.error("Don't have something here for Expression {}", selectExpression.getExpression());
    }
  }

  @Override
  public void visit(Cast cast) {

  }

  @Override
  public void visit(Case caseExpr) {

  }

  @Override
  public void visit(RelationExpression relationExpression) {
    supported = false;
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
}
