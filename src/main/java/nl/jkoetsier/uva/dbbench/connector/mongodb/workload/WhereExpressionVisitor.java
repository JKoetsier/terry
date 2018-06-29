package nl.jkoetsier.uva.dbbench.connector.mongodb.workload;

import com.mongodb.BasicDBObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.BetweenExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.BinExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Case;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Cast;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.DateExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.ExistsExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.ExpressionList;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.ExtractExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FieldExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FunctionExpr;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.InExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.IntervalExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.IsNullExpr;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.LikeExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.NullValue;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.RelationExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.SelectAllColumnsExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.SelectExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.StarExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.Constant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.DateConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.DoubleConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.LongConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.StringConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.AndOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.DivideOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.EqualsOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.GtOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.GteOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.LtOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.LteOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.MinusOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.ModuloOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.MultiplyOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.NeqOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.Operator;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.OrOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.PlusOp;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FullJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.InnerJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.InputRelation;
import nl.jkoetsier.uva.dbbench.internal.workload.query.OuterJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Projection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Rename;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Selection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Union;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.ExpressionVisitorAdapter;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhereExpressionVisitor extends ExpressionVisitorAdapter {

  private static Logger logger = LoggerFactory.getLogger(WhereExpressionVisitor.class);

  private Stack<Object> stack = new Stack<>();

  public BasicDBObject getWhereExpression() {
    Object fromStack = stack.pop();
    return (BasicDBObject)fromStack;
  }


  @Override
  public void visit(DateConstant dateConstant) {
    stack.push(dateConstant.getValue());
  }

  @Override
  public void visit(DoubleConstant doubleConstant) {
    stack.push(doubleConstant.getValue());
  }

  @Override
  public void visit(LongConstant longConstant) {
    stack.push(longConstant.getValue());
  }

  @Override
  public void visit(NullValue nullConstant) {
    stack.push("null");
  }

  @Override
  public void visit(StringConstant stringConstant) {
    stack.push(stringConstant.getValue());
  }

  @Override
  public void visit(BinExpression binExpression) {
    OperatorVisitor operatorVisitor = new OperatorVisitor();
    binExpression.getOperator().acceptVisitor(operatorVisitor);

    String operator = operatorVisitor.getOperator();
    Object rightExpr = stack.pop();
    Object leftExpr = stack.pop();
    Object newObject;

    if (binExpression.getOperator().isRelational()) {

      if (operator == null) {

        // Equals
        newObject = new BasicDBObject(leftExpr.toString(), rightExpr);


      } else {
        // Other

        newObject = new BasicDBObject(leftExpr.toString(), new BasicDBObject(operator, rightExpr));
      }

    } else if (binExpression.getOperator().isLogical()) {
      List<Object> list = new ArrayList<>();
      list.add(leftExpr);
      list.add(rightExpr);

      newObject = new BasicDBObject(operator, list);
    } else {
      newObject = new BasicDBObject();
    }

    if (binExpression.isNot()) {
      newObject = new BasicDBObject("$not", newObject);
    }

    stack.push(newObject);
  }

  @Override
  public void visit(FieldExpression fieldExpression) {
    stack.push(fieldExpression.getColumnName());
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
