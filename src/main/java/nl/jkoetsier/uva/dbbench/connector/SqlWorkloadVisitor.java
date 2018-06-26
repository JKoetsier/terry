package nl.jkoetsier.uva.dbbench.connector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import nl.jkoetsier.uva.dbbench.internal.SqlQuery;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.BinExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Case;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Cast;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.ExpressionList;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FieldExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FunctionExpr;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.InExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.IsNullExpr;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.NullValue;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.RelationExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.SelectAllColumnsExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.SelectExpression;
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
import nl.jkoetsier.uva.dbbench.internal.workload.query.Rename;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Selection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Union;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SqlWorkloadVisitor extends WorkloadVisitor {

  protected Logger logger = LoggerFactory.getLogger(SqlWorkloadVisitor.class);

  protected List<SqlQuery> result = new ArrayList<>();
  protected Stack<String> currentStack = new Stack<>();

  // Flag can be set by child visitors when an unsupported action is encountered. Will be set on
  // output queries.
  protected boolean supported = true;

  protected abstract SqlIdentifierQuoter getIdentifierQuoter();

  public List<SqlQuery> getResult() {
    return result;
  }

  public String quoteString(String input) {
    return getIdentifierQuoter().quoteString(input);
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
    currentStack.push(String.format("CAST(%s AS %s)", currentStack.pop(), cast.getType()));
  }

  @Override
  public void visit(BinExpression binExpression) {
    String operator = currentStack.pop();
    String rightExpr = currentStack.pop();
    String leftExpr = currentStack.pop();

    currentStack.push(String.format("%s(%s %s %s)", binExpression.isNot() ? "NOT " : "",
        leftExpr, operator, rightExpr));
  }

  @Override
  public void visit(OuterJoin outerJoin) {
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
  public void visit(FullJoin fullJoin) {
    String onExpr = "";

    if (fullJoin.getOnExpression() != null) {
      onExpr = String.format(" ON %s", currentStack.pop());
    }

    String rightInp = currentStack.pop();
    String leftInp = currentStack.pop();

    currentStack.push(String.format("%s FULL JOIN %s%s", leftInp, rightInp, onExpr));
  }

  @Override
  public void visit(Selection selection) {
    String where = "";

    if (selection.getWhereExpression() != null) {
      where = String.format(" WHERE %s", currentStack.pop());
    }

    String str = String.format("%s%s", currentStack.pop(), where);

    currentStack.push(str);
  }

  @Override
  public void visit(Union union) {
    String right = currentStack.pop();
    String left = currentStack.pop();

    currentStack.push(String.format("%s UNION%s %s", left, union.isAll() ? " ALL" : "", right));
  }

  @Override
  public void visit(Workload workload) {
  }

  @Override
  public void visit(FunctionExpr functionExpr) {
    String arguments = currentStack.pop();

    currentStack.push(String.format("%s(%s)", functionExpr.getName(), arguments));
  }

  @Override
  public void visit(ExpressionList expressionList) {
    List<String> fromStack = new ArrayList<>();

    for (int i = 0; i < expressionList.getExpressions().size(); i++) {
      fromStack.add(currentStack.pop());
    }

    Collections.reverse(fromStack);
    currentStack.push(String.join(",", fromStack));
  }

  @Override
  public void visit(IsNullExpr isNullExpr) {
    String leftExpr = currentStack.pop();

    currentStack.push(String.format("%s IS%s NULL", leftExpr, isNullExpr.isNot() ? " NOT" : ""));
  }

  @Override
  public void visit(Rename rename) {
    currentStack.push(String.format("%s AS %s", currentStack.pop(), rename.getName()));
  }

  @Override
  public void visit(Case caseExpr) {
    String falseExpr = currentStack.pop();
    String trueExpr = currentStack.pop();
    String condition = currentStack.pop();

    currentStack.push(String.format("CASE WHEN %s THEN %s ELSE %s END",
        condition, trueExpr, falseExpr));
  }

  @Override
  public void visit(Query query) {
    assert currentStack.size() == 1;

    String queryString = currentStack.pop();

    // All SELECT queries are wrapped in ( ) . Unwrap the first (or outer) query. In case of a
    // UNION, first SELECT will be unwrapped, second will still be wrapped in parentheses.
    if (queryString.charAt(0) == '(') {
      int cnt = 1;

      for (int i = 1; i < queryString.length(); i++) {
        if (queryString.charAt(i) == '(') {
          cnt++;
        } else if (queryString.charAt(i) == ')') {
          cnt--;
        }

        if (cnt == 0) {
          String firstPart = queryString.substring(1, i);
          String lastPart = queryString.substring(i + 1, queryString.length());

          queryString = firstPart + lastPart;
          break;
        }

      }
    }

    SqlQuery sqlQuery = new SqlQuery(query);
    sqlQuery.setQueryString(queryString);
    sqlQuery.setSupported(supported);

    result.add(sqlQuery);
    supported = true;
  }

  @Override
  public void visit(InnerJoin innerJoin) {
    String onExpr = "";

    if (innerJoin.getOnExpression() != null) {
      onExpr = String.format(" ON %s", currentStack.pop());
    }

    String rightInp = currentStack.pop();
    String leftInp = currentStack.pop();

    currentStack.push(String.format("%s INNER JOIN %s%s", leftInp, rightInp, onExpr));
  }

  @Override
  public void visit(SelectExpression selectExpression) {
    String alias = "";

    if (selectExpression.getAlias() != null) {
      alias = String.format(" AS %s", quoteString(selectExpression.getAlias()));
    }

    currentStack.push(String.format("%s%s", currentStack.pop(), alias));
  }

  @Override
  public void visit(FieldExpression fieldExpression) {
    String[] fieldName = fieldExpression.getFieldName().split("\\.");

    for (int i = 0; i < fieldName.length; i++) {
      fieldName[i] = quoteString(fieldName[i]);
    }

    currentStack.push(String.format("%s", String.join(".", fieldName)));
  }

  @Override
  public void visit(InputRelation inputRelation) {
    if (inputRelation.getTableAlias() != null) {
      currentStack.push(String.format("%s AS %s", quoteString(inputRelation.getTableName()),
          quoteString(inputRelation.getTableAlias())));
    } else {
      currentStack.push(String.format("%s", quoteString(inputRelation.getTableName())));
    }
  }

  @Override
  public void visit(RelationExpression relationExpression) {
    currentStack.push(String.format("(%s)", currentStack.pop()));
  }

  @Override
  public void visit(InExpression inExpression) {
    String right = currentStack.pop();
    String left = currentStack.pop();

    currentStack.push(String.format("%s IN (%s)", left, right));
  }

  @Override
  public void visit(SelectAllColumnsExpression selectAllColumnsExpression) {
    currentStack
        .push(String.format("%s.*", quoteString(selectAllColumnsExpression.getTableName())));
  }

  @Override
  public void visit(Projection projection) {
    String limit = "";
    String orderBy = "";

    if (projection.getLimit() != null) {
      limit = String.format(" LIMIT %s", currentStack.pop());

      if (projection.getOffset() != null) {
        limit = limit.concat(String.format(" OFFSET %s", currentStack.pop()));
      }
    }

    if (projection.getOrderBy() != null) {
      List<String> orderByList = projection.getOrderByAsStrings();
      orderBy = String.format(" ORDER BY %s", String.join(", ", orderByList));
    }

    List<String> selectFields = new ArrayList<>();
    String select;

    if (projection.getSelectExpressions() != null) {
      for (SelectExpression selectExpression : projection.getSelectExpressions()) {
        selectFields.add(currentStack.pop());
      }
    }

    Collections.reverse(selectFields);

    if (selectFields.size() > 0) {
      select = String.join(", ", selectFields);
    } else {
      select = "*";
    }

    String from = currentStack.pop();

    String str = String.format(
        "(SELECT%s %s FROM %s%s%s)",
        projection.isDistinct() ? " DISTINCT" : "",
        select,
        from,
        orderBy,
        limit
    );

    currentStack.push(str);
  }
}
