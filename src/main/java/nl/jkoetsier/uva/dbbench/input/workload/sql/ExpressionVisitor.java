package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.select.SelectVisitor;

public class ExpressionVisitor extends ExpressionVisitorAdapter {

    @Override
    public SelectVisitor getSelectVisitor() {
        return super.getSelectVisitor();
    }

    @Override
    public void setSelectVisitor(SelectVisitor selectVisitor) {
        super.setSelectVisitor(selectVisitor);
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
        super.visit(value);
    }

    @Override
    public void visit(LongValue value) {
        super.visit(value);
    }

    @Override
    public void visit(DateValue value) {
        super.visit(value);
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
        super.visit(value);
    }

    @Override
    public void visit(Addition expr) {
        super.visit(expr);
    }

    @Override
    public void visit(Division expr) {
        super.visit(expr);
    }

    @Override
    public void visit(Multiplication expr) {
        super.visit(expr);
    }

    @Override
    public void visit(Subtraction expr) {
        super.visit(expr);
    }

    @Override
    public void visit(AndExpression expr) {
        super.visit(expr);
    }

    @Override
    public void visit(OrExpression expr) {
        super.visit(expr);
    }

    @Override
    public void visit(Between expr) {
        super.visit(expr);
    }

    @Override
    public void visit(EqualsTo expr) {
        super.visit(expr);
    }

    @Override
    public void visit(GreaterThan expr) {
        super.visit(expr);
    }

    @Override
    public void visit(GreaterThanEquals expr) {
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
    public void visit(MinorThan expr) {
        super.visit(expr);
    }

    @Override
    public void visit(MinorThanEquals expr) {
        super.visit(expr);
    }

    @Override
    public void visit(NotEqualsTo expr) {
        super.visit(expr);
    }

    @Override
    public void visit(Column column) {
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
    public void visit(AllComparisonExpression expr) {
        super.visit(expr);
    }

    @Override
    public void visit(AnyComparisonExpression expr) {
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
    public void visit(Modulo expr) {
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
    public void visit(BitwiseRightShift expr) {
        super.visit(expr);
    }

    @Override
    public void visit(BitwiseLeftShift expr) {
        super.visit(expr);
    }

    @Override
    protected void visitBinaryExpression(BinaryExpression expr) {
        super.visitBinaryExpression(expr);
    }

    @Override
    public void visit(JsonExpression jsonExpr) {
        super.visit(jsonExpr);
    }

    @Override
    public void visit(JsonOperator expr) {
        super.visit(expr);
    }

    @Override
    public void visit(RegExpMySQLOperator expr) {
        super.visit(expr);
    }

    @Override
    public void visit(UserVariable var) {
        super.visit(var);
    }

    @Override
    public void visit(NumericBind bind) {
        super.visit(bind);
    }

    @Override
    public void visit(KeepExpression expr) {
        super.visit(expr);
    }

    @Override
    public void visit(MySQLGroupConcat groupConcat) {
        super.visit(groupConcat);
    }

    @Override
    public void visit(ValueListExpression valueListExpression) {
        super.visit(valueListExpression);
    }

    @Override
    public void visit(Pivot pivot) {
        super.visit(pivot);
    }

    @Override
    public void visit(PivotXml pivot) {
        super.visit(pivot);
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

    @Override
    public void visit(RowConstructor rowConstructor) {
        super.visit(rowConstructor);
    }

    @Override
    public void visit(HexValue hexValue) {
        super.visit(hexValue);
    }

    @Override
    public void visit(OracleHint hint) {
        super.visit(hint);
    }

    @Override
    public void visit(TimeKeyExpression timeKeyExpression) {
        super.visit(timeKeyExpression);
    }

    @Override
    public void visit(DateTimeLiteralExpression literal) {
        super.visit(literal);
    }
}
