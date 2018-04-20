package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.expression.AnalyticExpression;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.CastExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.ExtractExpression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.IntervalExpression;
import net.sf.jsqlparser.expression.JdbcNamedParameter;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NotExpression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.OracleHierarchicalExpression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.SignedExpression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.UserVariable;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.expression.operators.relational.RegExpMatchOperator;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SubSelect;
import nl.jkoetsier.uva.dbbench.input.exception.InvalidQueryException;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.BinExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Expression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FieldExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.DateConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.DoubleConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.LongConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.StringConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.Operator;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.OperatorFactory;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FieldRef;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;

public class ExpressionVisitor extends ExpressionVisitorAdapter {

  private Expression expression;
  private Relation relation;

  // Relation to check for variables etc
  public ExpressionVisitor(Relation relation) {
    expression = null;
    this.relation = relation;
  }

  public Expression getExpression() {
    return expression;
  }

  public void reset() {
    expression = null;
  }

  @Override
  public void visit(NullValue value) {
    super.visit(value);
  }

  @Override
  public void visit(Function function) {
    super.visit(function);
  }

  @Override
  public void visit(SignedExpression expr) {
    super.visit(expr);
  }

  @Override
  public void visit(JdbcParameter parameter) {
    super.visit(parameter);
  }

  @Override
  public void visit(JdbcNamedParameter parameter) {
    super.visit(parameter);
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
  public void visit(TimeValue value) {
    super.visit(value);
  }

  @Override
  public void visit(TimestampValue value) {
    super.visit(value);
  }

  @Override
  public void visit(Parenthesis parenthesis) {
    super.visit(parenthesis);
  }

  @Override
  public void visit(StringValue value) {
    expression = new StringConstant(value.getValue());
  }

  @Override
  public void visit(Between expr) {
    super.visit(expr);
  }

  @Override
  public void visit(InExpression expr) {
    super.visit(expr);
  }

  @Override
  public void visit(IsNullExpression expr) {
    super.visit(expr);
  }

  @Override
  public void visit(LikeExpression expr) {
    super.visit(expr);
  }

  @Override
  public void visit(Column column) {
    FieldRef fieldRef = relation.getFieldRef(column.getFullyQualifiedName());

    if (fieldRef == null) {
      throw new InvalidQueryException(String.format(
          "Column '%s' does not exist", column.getFullyQualifiedName()
      ));
    }

    expression = new FieldExpression(fieldRef);

    super.visit(column);
  }

  @Override
  public void visit(SubSelect subSelect) {
    super.visit(subSelect);
  }

  @Override
  public void visit(CaseExpression expr) {
    super.visit(expr);
  }

  @Override
  public void visit(WhenClause expr) {
    super.visit(expr);
  }

  @Override
  public void visit(ExistsExpression expr) {
    super.visit(expr);
  }

  @Override
  public void visit(Concat expr) {
    super.visit(expr);
  }

  @Override
  public void visit(Matches expr) {
    super.visit(expr);
  }

  @Override
  public void visit(BitwiseAnd expr) {
    super.visit(expr);
  }

  @Override
  public void visit(BitwiseOr expr) {
    super.visit(expr);
  }

  @Override
  public void visit(BitwiseXor expr) {
    super.visit(expr);
  }

  @Override
  public void visit(CastExpression expr) {
    super.visit(expr);
  }

  @Override
  public void visit(AnalyticExpression expr) {
    super.visit(expr);
  }

  @Override
  public void visit(ExtractExpression expr) {
    super.visit(expr);
  }

  @Override
  public void visit(IntervalExpression expr) {
    super.visit(expr);
  }

  @Override
  public void visit(OracleHierarchicalExpression expr) {
    super.visit(expr);
  }

  @Override
  public void visit(RegExpMatchOperator expr) {
    super.visit(expr);
  }

  @Override
  public void visit(ExpressionList expressionList) {
    super.visit(expressionList);
  }

  @Override
  public void visit(MultiExpressionList multiExprList) {
    super.visit(multiExprList);
  }

  @Override
  public void visit(NotExpression notExpr) {
    super.visit(notExpr);
  }

  @Override
  protected void visitBinaryExpression(BinaryExpression expr) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor(relation);
    expr.getLeftExpression().accept(expressionVisitor);
    Expression leftExpr = expressionVisitor.getExpression();

    expressionVisitor.reset();

    expr.getRightExpression().accept(expressionVisitor);
    Expression rightExpr = expressionVisitor.getExpression();

    Operator operator = OperatorFactory.create(expr.getStringExpression());

    expression = new BinExpression(
        leftExpr,
        rightExpr,
        operator,
        expr.isNot()
    );
  }

  @Override
  public void visit(UserVariable var) {
    super.visit(var);
  }

  @Override
  public void visit(AllColumns allColumns) {
    super.visit(allColumns);
  }

  @Override
  public void visit(AllTableColumns allTableColumns) {
    super.visit(allTableColumns);
  }

  @Override
  public void visit(SelectExpressionItem selectExpressionItem) {
    super.visit(selectExpressionItem);
  }


}
