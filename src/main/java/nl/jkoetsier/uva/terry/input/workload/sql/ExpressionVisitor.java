package nl.jkoetsier.uva.terry.input.workload.sql;

import java.util.ArrayList;
import java.util.List;
import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnalyticExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.CastExpression;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.ExtractExpression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.HexValue;
import net.sf.jsqlparser.expression.IntervalExpression;
import net.sf.jsqlparser.expression.JdbcNamedParameter;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.JsonExpression;
import net.sf.jsqlparser.expression.KeepExpression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.MySQLGroupConcat;
import net.sf.jsqlparser.expression.NotExpression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.NumericBind;
import net.sf.jsqlparser.expression.OracleHierarchicalExpression;
import net.sf.jsqlparser.expression.OracleHint;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.RowConstructor;
import net.sf.jsqlparser.expression.SignedExpression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeKeyExpression;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.UserVariable;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.WithinGroupExpression;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Modulo;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.JsonOperator;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.expression.operators.relational.RegExpMatchOperator;
import net.sf.jsqlparser.expression.operators.relational.RegExpMySQLOperator;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.Pivot;
import net.sf.jsqlparser.statement.select.PivotXml;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SubSelect;
import nl.jkoetsier.uva.terry.input.util.StringUtil;
import nl.jkoetsier.uva.terry.intrep.workload.expression.BetweenExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.BinExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.Case;
import nl.jkoetsier.uva.terry.intrep.workload.expression.Cast;
import nl.jkoetsier.uva.terry.intrep.workload.expression.DateExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.Expression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.FieldExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.FunctionExpr;
import nl.jkoetsier.uva.terry.intrep.workload.expression.IsNullExpr;
import nl.jkoetsier.uva.terry.intrep.workload.expression.RelationExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.StarExpression;
import nl.jkoetsier.uva.terry.intrep.workload.expression.constant.DateConstant;
import nl.jkoetsier.uva.terry.intrep.workload.expression.constant.DoubleConstant;
import nl.jkoetsier.uva.terry.intrep.workload.expression.constant.LongConstant;
import nl.jkoetsier.uva.terry.intrep.workload.expression.constant.StringConstant;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.Operator;
import nl.jkoetsier.uva.terry.intrep.workload.expression.operator.OperatorFactory;

public class ExpressionVisitor extends ExpressionVisitorAdapter {

  private Expression expression;

  // Relation to check for variables etc
  public ExpressionVisitor() {
    expression = null;
  }

  public Expression getExpression() {
    return expression;
  }

  public void reset() {
    expression = null;
  }

  @Override
  public void visit(DoubleValue value) {
    expression = new DoubleConstant(value.getValue());
  }

  @Override
  public void visit(LongValue value) {
    expression = new LongConstant(value.getValue());
  }

  @Override
  public void visit(DateValue value) {
    expression = new DateConstant(value.getValue());
  }

  @Override
  public void visit(StringValue value) {
    expression = new StringConstant(value.getValue());
  }

  @Override
  public void visit(Column column) {
    expression = new FieldExpression(StringUtil.unEscapeIdentifier(column.getFullyQualifiedName()));
  }

  @Override
  protected void visitBinaryExpression(BinaryExpression expr) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    expr.getLeftExpression().accept(expressionVisitor);
    Expression leftExpr = expressionVisitor.getExpression();

    expressionVisitor.reset();

    expr.getRightExpression().accept(expressionVisitor);
    Expression rightExpr = expressionVisitor.getExpression();

    Operator operator = OperatorFactory.create(expr.getStringExpression());

    assert leftExpr != null;
    assert rightExpr != null;

    expression = new BinExpression(
        leftExpr,
        rightExpr,
        operator,
        expr.isNot()
    );
  }

  @Override
  public void visit(NullValue value) {
    expression = new nl.jkoetsier.uva.terry.intrep.workload.expression.NullValue();
  }

  @Override
  public void visit(EqualsTo expr) {
    visitBinaryExpression(expr);
  }

  @Override
  public void visit(SignedExpression expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(JdbcParameter parameter) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(JdbcNamedParameter parameter) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(TimeValue value) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(TimestampValue value) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(Parenthesis parenthesis) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    parenthesis.getExpression().accept(expressionVisitor);

    expression = expressionVisitor.getExpression();
    expression.setNot(parenthesis.isNot());
  }

  @Override
  public void visit(Addition expr) {
    visitBinaryExpression(expr);
  }

  @Override
  public void visit(Division expr) {
    visitBinaryExpression(expr);
  }

  @Override
  public void visit(Multiplication expr) {
    visitBinaryExpression(expr);
  }

  @Override
  public void visit(Subtraction expr) {
    visitBinaryExpression(expr);
  }

  @Override
  public void visit(AndExpression expr) {
    visitBinaryExpression(expr);
  }

  @Override
  public void visit(OrExpression expr) {
    visitBinaryExpression(expr);
  }

  @Override
  public void visit(Between expr) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    expr.getLeftExpression().accept(expressionVisitor);
    Expression subjectExpr = expressionVisitor.getExpression();

    expressionVisitor.reset();

    expr.getBetweenExpressionStart().accept(expressionVisitor);
    Expression leftBetweenExpr = expressionVisitor.getExpression();

    expressionVisitor.reset();

    expr.getBetweenExpressionEnd().accept(expressionVisitor);
    Expression rightBetweenExpr = expressionVisitor.getExpression();

    expression = new BetweenExpression(subjectExpr, leftBetweenExpr, rightBetweenExpr, expr.isNot());
  }

  @Override
  public void visit(GreaterThan expr) {
    visitBinaryExpression(expr);
  }

  @Override
  public void visit(GreaterThanEquals expr) {
    visitBinaryExpression(expr);
  }

  @Override
  public void visit(InExpression expr) {
    nl.jkoetsier.uva.terry.intrep.workload.expression.InExpression inExpression
        = new nl.jkoetsier.uva.terry.intrep.workload.expression.InExpression();

    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    expr.getLeftExpression().accept(expressionVisitor);
    inExpression.setLeftExpression(expressionVisitor.getExpression());

    ItemsListVisitor itemsListVisitor = new ItemsListVisitor();
    expr.getRightItemsList().accept(itemsListVisitor);
    inExpression.setRightExpression(itemsListVisitor.getExpression());

    inExpression.setNot(expr.isNot());

    expression = inExpression;
    // Ignoring expr.getLeftItemsList(). What is it??
  }

  @Override
  public void visit(IsNullExpression expr) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    expr.getLeftExpression().accept(expressionVisitor);

    expression = new IsNullExpr(expressionVisitor.getExpression(), expr.isNot());
  }

  @Override
  public void visit(LikeExpression expr) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    expr.getLeftExpression().accept(expressionVisitor);

    Expression leftExpr = expressionVisitor.getExpression();
    expressionVisitor.reset();
    expr.getRightExpression().accept(expressionVisitor);

    Expression rightExpr = expressionVisitor.getExpression();

    expression = new nl.jkoetsier.uva.terry.intrep.workload.expression.LikeExpression(
        leftExpr,
        rightExpr,
        expr.isNot()
    );
  }

  @Override
  public void visit(MinorThan expr) {
    visitBinaryExpression(expr);
  }

  @Override
  public void visit(MinorThanEquals expr) {
    visitBinaryExpression(expr);
  }

  @Override
  public void visit(NotEqualsTo expr) {
    visitBinaryExpression(expr);
  }

  @Override
  public void visit(SubSelect subSelect) {
    SelectVisitor selectVisitor = new SelectVisitor();
    subSelect.getSelectBody().accept(selectVisitor);

    expression = new RelationExpression(selectVisitor.getRelation());
  }

  @Override
  public void visit(CaseExpression expr) {
    Case caseExpr = new Case();

    ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    for (net.sf.jsqlparser.expression.Expression whenExpr : expr.getWhenClauses()) {
      WhenClause whenClause = (WhenClause) whenExpr;

      if (whenClause.getThenExpression() != null) {
        whenClause.getThenExpression().accept(expressionVisitor);
        caseExpr.setTrueExpression(expressionVisitor.getExpression());
      }

      if (whenClause.getWhenExpression() != null) {
        whenClause.getWhenExpression().accept(expressionVisitor);
        caseExpr.setCondition(expressionVisitor.getExpression());
      }
    }

    expr.getElseExpression().accept(expressionVisitor);
    caseExpr.setFalseExpression(expressionVisitor.getExpression());

    expression = caseExpr;
  }

  @Override
  public void visit(WhenClause expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(ExistsExpression expr) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    expr.getRightExpression().accept(expressionVisitor);

    expression = new nl.jkoetsier.uva.terry.intrep.workload.expression.ExistsExpression(
        expressionVisitor.getExpression(),
        expr.isNot()
    );
  }

  @Override
  public void visit(AllComparisonExpression expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(AnyComparisonExpression expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(Concat expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(Matches expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(BitwiseAnd expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(BitwiseOr expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(BitwiseXor expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(CastExpression expr) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    expr.getLeftExpression().accept(expressionVisitor);

    expression = new Cast(expressionVisitor.getExpression(), expr.getType().getDataType());
  }

  @Override
  public void visit(Modulo expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(AnalyticExpression expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(ExtractExpression expr) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    expr.getExpression().accept(expressionVisitor);

    Expression fromExpr = expressionVisitor.getExpression();

    expression = new nl.jkoetsier.uva.terry.intrep.workload.expression.ExtractExpression(
        expr.getName(),
        fromExpr
    );
  }

  @Override
  public void visit(IntervalExpression expr) {
    expression = new nl.jkoetsier.uva.terry.intrep.workload.expression.IntervalExpression(
        expr.getParameter(), expr.getIntervalType()
    );
  }

  @Override
  public void visit(OracleHierarchicalExpression expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(RegExpMatchOperator expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(ExpressionList expressionList) {

    ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    List<Expression> expressions = new ArrayList<>();

    for (net.sf.jsqlparser.expression.Expression expr : expressionList.getExpressions()) {
      expressionVisitor.reset();
      expr.accept(expressionVisitor);
      expressions.add(expressionVisitor.getExpression());
    }

    expression = new nl.jkoetsier.uva.terry.intrep.workload.expression.ExpressionList(
        expressions);
  }

  @Override
  public void visit(MultiExpressionList multiExprList) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(NotExpression notExpr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(JsonExpression jsonExpr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(JsonOperator expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(RegExpMySQLOperator expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(WithinGroupExpression wgexpr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(UserVariable var) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(NumericBind bind) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(KeepExpression expr) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(MySQLGroupConcat groupConcat) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(Pivot pivot) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(PivotXml pivot) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(AllColumns allColumns) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(AllTableColumns allTableColumns) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(SelectExpressionItem selectExpressionItem) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(RowConstructor rowConstructor) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(HexValue hexValue) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(OracleHint hint) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(TimeKeyExpression timeKeyExpression) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void visit(DateTimeLiteralExpression literal) {
    expression = new DateExpression(literal.getValue());
  }

  @Override
  public void visit(Function function) {
    List<Expression> expressions = new ArrayList<>();
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    if (function.getParameters() != null) {
      for (net.sf.jsqlparser.expression.Expression expr : function.getParameters().getExpressions()) {
        expressionVisitor.reset();
        expr.accept(expressionVisitor);
        expressions.add(expressionVisitor.getExpression());
      }
    } else if (function.isAllColumns()) {
      expressions.add(new StarExpression());
    }

    FunctionExpr functionExpr = new FunctionExpr(function.getName(),
        new nl.jkoetsier.uva.terry.intrep.workload.expression.ExpressionList(expressions));

    if (function.isDistinct()) {
      functionExpr.setDistinct(true);
    }

    expression = functionExpr;
  }
}
