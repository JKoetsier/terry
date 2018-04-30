package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.OrderByVisitorAdapter;
import nl.jkoetsier.uva.dbbench.internal.workload.element.OrderBy;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FieldExpression;

public class OrderByVisitor extends OrderByVisitorAdapter {

  private OrderBy orderBy;

  public OrderBy getOrderBy() {
    return orderBy;
  }

  @Override
  public void visit(OrderByElement orderBy) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    orderBy.getExpression().accept(expressionVisitor);
    assert expressionVisitor.getExpression() instanceof FieldExpression;

    this.orderBy = new OrderBy((FieldExpression) expressionVisitor.getExpression(),
        orderBy.isAsc() ? OrderBy.Direction.ASC : OrderBy.Direction.DESC);
  }
}
