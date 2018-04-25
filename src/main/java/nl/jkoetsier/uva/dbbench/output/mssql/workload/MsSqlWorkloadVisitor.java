package nl.jkoetsier.uva.dbbench.output.mssql.workload;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.BinExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.ExpressionList;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FieldExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FunctionExpr;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.IsNullExpr;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.NullValue;
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
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.OrOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.PlusOp;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FullJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.InnerJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.InputRelation;
import nl.jkoetsier.uva.dbbench.internal.workload.query.OuterJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Projection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Selection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Union;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsSqlWorkloadVisitor extends WorkloadVisitor {

  private List<String> result = new ArrayList<>();
  private Stack<String> currentStack = new Stack<>();
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public List<String> getResult() {
    return result;
  }

  @Override
  public void visit(DateConstant dateConstant) {
    currentStack.push(dateConstant.getValue().toString());
  }

  @Override
  public void visit(DoubleConstant doubleConstant) {
    currentStack.push(doubleConstant.getValue().toString());
  }

  @Override
  public void visit(LongConstant longConstant) {
    currentStack.push(longConstant.getValue().toString());
  }

  @Override
  public void visit(NullValue nullConstant) {
    currentStack.push("NULL");
  }

  @Override
  public void visit(StringConstant stringConstant) {
    currentStack.push(stringConstant.getValue());
  }

  @Override
  public void visit(AndOp andOp) {
    currentStack.push("AND");
  }

  @Override
  public void visit(DivideOp divideOp) {
    currentStack.push("/");
  }

  @Override
  public void visit(EqualsOp equalsOp) {
    currentStack.push("=");
  }

  @Override
  public void visit(GteOp gteOp) {
    currentStack.push(">=");
  }

  @Override
  public void visit(GtOp gtOp) {
    currentStack.push(">");
  }

  @Override
  public void visit(LteOp lteOp) {
    currentStack.push("<=");
  }

  @Override
  public void visit(LtOp ltOp) {
    currentStack.push("<");
  }

  @Override
  public void visit(MinusOp minusOp) {
    currentStack.push("-");
  }

  @Override
  public void visit(ModuloOp moduloOp) {
    currentStack.push("%");
  }

  @Override
  public void visit(MultiplyOp multiplyOp) {
    currentStack.push("*");
  }

  @Override
  public void visit(NeqOp neqOp) {
    currentStack.push("!=");
  }

  @Override
  public void visit(OrOp orOp) {
    currentStack.push("OR");
  }

  @Override
  public void visit(PlusOp plusOp) {
    currentStack.push("+");
  }

  @Override
  public void visit(BinExpression binExpression) {
    String operator = currentStack.pop();
    String rightExpr = currentStack.pop();
    String leftExpr = currentStack.pop();

    currentStack.push(String.format("%s %s %s", leftExpr, operator, rightExpr));
  }

  @Override
  public void visit(FieldExpression fieldExpression) {
    currentStack.push(fieldExpression.getFieldName());
  }

  @Override
  public void visit(FullJoin fullJoin) {
    logger.info("Visit fullJoin");

    String onExpr = "";

    if (fullJoin.getOnExpression() != null) {
      onExpr = String.format(" ON %s", currentStack.pop());
    }

    String rightInp = currentStack.pop();
    currentStack.pop(); // pop left input

    currentStack.push(String.format("FULL JOIN %s%s", rightInp, onExpr));

    throw new RuntimeException("Not implemented");
  }

  @Override
  public void visit(InnerJoin innerJoin) {
    logger.info("Visit innerJoin");

    String onExpr = "";

    if (innerJoin.getOnExpression() != null) {
      onExpr = String.format(" ON %s", currentStack.pop());
    }

    String rightInp = currentStack.pop();
    currentStack.pop(); // pop left input

    currentStack.push(String.format("INNER JOIN %s%s", rightInp, onExpr));
  }

  @Override
  public void visit(InputRelation inputRelation) {
    logger.info("Visit inputRelation");

    if (inputRelation.getTableAlias() != null) {
      currentStack.push(String.format("%s AS %s", inputRelation.getTableName(),
          inputRelation.getTableAlias()));
    } else {
      currentStack.push(inputRelation.getTableName());
    }
  }

  @Override
  public void visit(OuterJoin outerJoin) {
    logger.info("Visit OUTER JOIN");

    String onExpr = "";

    if (outerJoin.getOnExpression() != null) {
      onExpr = String.format(" ON %s", currentStack.pop());
    }

    String rightInp = currentStack.pop();
    String leftInp = currentStack.pop();

    currentStack.push(String.format("%s %s OUTER JOIN %s%s", leftInp, outerJoin.getDirection(),
        rightInp, onExpr));
  }

  @Override
  public void visit(Projection projection) {
    logger.info("Visit Projection");

    String str = String.format("SELECT %s FROM %s",
        projection.getFieldRefString(),
        currentStack.pop());

    currentStack.push(str);
  }

  @Override
  public void visit(Selection selection) {
    logger.info("Visit Selection");

    String where = "";

    if (selection.getWhereExpression() != null) {
      where = String.format(" WHERE %s", currentStack.pop());
    }

    String str = String.format("%s%s", currentStack.pop(), where);

    currentStack.push(str);
  }

  @Override
  public void visit(Union union) {
    logger.info("Visit Union");

    String right = currentStack.pop();
    String left = currentStack.pop();

    currentStack.push(String.format("%s UNION%s %s", left, union.isAll() ? " ALL" : "", right));
  }

  @Override
  public void visit(Query query) {
    logger.info("Visit query");

    logger.info("Stack size on end Query: {}", currentStack.size());
    logger.info("Peek stack: {}", currentStack.peek());
    assert currentStack.size() == 1;

    result.add(currentStack.pop());
  }

  @Override
  public void visit(Workload workload) {
    logger.info("Visit workload");
  }

  @Override
  public void visit(FunctionExpr functionExpr) {
    logger.info("Visit FunctionExpr");

  }

  @Override
  public void visit(ExpressionList expressionList) {
    logger.info("Visit ExpressionList");
  }

  @Override
  public void visit(IsNullExpr isNullExpr) {
    logger.info("Visit IsNull");
  }
}
