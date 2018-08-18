package nl.jkoetsier.uva.terry.input.workload.sql;

import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.OrderByVisitorAdapter;
import nl.jkoetsier.uva.terry.intrep.workload.element.OrderBy;
import nl.jkoetsier.uva.terry.intrep.workload.expression.ColumnNameExpression;

public class OrderByVisitor extends OrderByVisitorAdapter {

  private OrderBy orderBy;

  public OrderBy getOrderBy() {
    return orderBy;
  }

  @Override
  public void visit(OrderByElement orderBy) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    orderBy.getExpression().accept(expressionVisitor);
    assert expressionVisitor.getExpression() instanceof ColumnNameExpression;

    this.orderBy = new OrderBy((ColumnNameExpression) expressionVisitor.getExpression(),
        orderBy.isAsc() ? OrderBy.Direction.ASC : OrderBy.Direction.DESC);
  }
}
