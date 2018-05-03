package nl.jkoetsier.uva.dbbench.output;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.BinExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Case;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Cast;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.ExpressionList;
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
import nl.jkoetsier.uva.dbbench.internal.workload.query.InnerJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.OuterJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Rename;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Selection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Union;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
import nl.jkoetsier.uva.dbbench.output.mssql.workload.MsSqlWorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseSqlWorkloadVisitor extends WorkloadVisitor {

  protected Logger logger = LoggerFactory.getLogger(BaseSqlWorkloadVisitor.class);

  protected HashMap<Integer, String> result = new HashMap<>();
  protected Stack<String> currentStack = new Stack<>();

  public HashMap<Integer, String> getResult() {
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
    logger.debug("Visit NullValue");

    currentStack.push("NULL");
  }

  @Override
  public void visit(StringConstant stringConstant) {
    currentStack.push(String.format("'%s'", stringConstant.getValue()));
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
  public void visit(Cast cast) {
    logger.debug("Visit Cast");

    currentStack.push(String.format("CAST(%s AS %s)", currentStack.pop(), cast.getType()));
  }

  @Override
  public void visit(BinExpression binExpression) {
    logger.debug("Visit BinExpression");
    String operator = currentStack.pop();
    String rightExpr = currentStack.pop();
    String leftExpr = currentStack.pop();

    currentStack.push(String.format("%s %s %s", leftExpr, operator, rightExpr));
  }

  @Override
  public void visit(OuterJoin outerJoin) {
    logger.debug("Visit OUTER JOIN");

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
  public void visit(Selection selection) {
    logger.debug("Visit Selection");

    String where = "";

    if (selection.getWhereExpression() != null) {
      where = String.format(" WHERE %s", currentStack.pop());
    }

    String str = String.format("%s%s", currentStack.pop(), where);

    currentStack.push(str);
  }

  @Override
  public void visit(Union union) {
    logger.debug("Visit Union");

    String right = currentStack.pop();
    String left = currentStack.pop();

    currentStack.push(String.format("%s UNION%s %s", left, union.isAll() ? " ALL" : "", right));
  }

  @Override
  public void visit(Workload workload) {
    logger.debug("Visit Workload");
  }

  @Override
  public void visit(FunctionExpr functionExpr) {
    logger.debug("Visit FunctionExpr");

    String arguments = currentStack.pop();

    currentStack.push(String.format("%s(%s)", functionExpr.getName(), arguments));
  }

  @Override
  public void visit(ExpressionList expressionList) {
    logger.debug("Visit ExpressionList");

    List<String> fromStack = new ArrayList<>();

    for (int i = 0; i < expressionList.getExpressions().size(); i++) {
      fromStack.add(currentStack.pop());
    }

    currentStack.push(String.join(",", fromStack));
  }

  @Override
  public void visit(IsNullExpr isNullExpr) {
    logger.debug("Visit IsNull");

    String leftExpr = currentStack.pop();

    currentStack.push(String.format("%s IS%s NULL", leftExpr, isNullExpr.isNot() ? " NOT" : ""));
  }

  @Override
  public void visit(Rename rename) {
    logger.debug("Visit Rename");

    currentStack.push(String.format("%s AS %s", currentStack.pop(), rename.getName()));
  }

  @Override
  public void visit(Case caseExpr) {
    logger.debug("Visit Case");

    String falseExpr = currentStack.pop();
    String trueExpr = currentStack.pop();
    String condition = currentStack.pop();

    currentStack.push(String.format("CASE WHEN %s THEN %s ELSE %s END",
        condition, trueExpr, falseExpr));
  }

  @Override
  public void visit(Query query) {
    logger.debug("Visit Query");

    assert currentStack.size() == 1;

    String queryString = currentStack.pop();

    result.put(query.getIdentifier(), queryString);
  }

  @Override
  public void visit(InnerJoin innerJoin) {
    logger.debug("Visit InnerJoin");

    String onExpr = "";

    if (innerJoin.getOnExpression() != null) {
      onExpr = String.format(" ON %s", currentStack.pop());
    }

    String rightInp = currentStack.pop();
    String leftInp = currentStack.pop();

    currentStack.push(String.format("%s INNER JOIN %s%s", leftInp, rightInp, onExpr));
  }
}
