package nl.jkoetsier.uva.dbbench.input.workload.sql;

import java.util.ArrayList;
import java.util.List;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SelectVisitorAdapter;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;
import nl.jkoetsier.uva.dbbench.internal.workload.element.OrderBy;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FullJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.InnerJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.OuterJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Projection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.RAJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Selection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Union;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectVisitor extends SelectVisitorAdapter {

  private Relation relation;
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public Relation getRelation() {
    return relation;
  }

  @Override
  public void visit(PlainSelect plainSelect) {
    Selection selection = new Selection();

    FromVisitor fromVisitor = new FromVisitor();
    plainSelect.getFromItem().accept(fromVisitor);

    selection.setInput(fromVisitor.getInputRelation());

    if (plainSelect.getJoins() != null) {
      for (Join join : plainSelect.getJoins()) {
        RAJoin raJoin = createJoin(join, selection.getInput());

        selection.setInput(raJoin);
      }
    }

    SelectItemVisitor selectItemVisitor = new SelectItemVisitor(selection);

    for (SelectItem selectItem : plainSelect.getSelectItems()) {
      selectItem.accept(selectItemVisitor);
    }

    ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    if (plainSelect.getWhere() != null) {
      plainSelect.getWhere().accept(expressionVisitor);
      selection.setWhereExpression(expressionVisitor.getExpression());
    }

    Projection projection = selectItemVisitor.getRelation();

    if (plainSelect.getOrderByElements() != null) {
      OrderByVisitor orderByVisitor = new OrderByVisitor();
      List<OrderBy> orderByList = new ArrayList<>();

      for (OrderByElement orderByElement : plainSelect.getOrderByElements()) {

        orderByElement.accept(orderByVisitor);
        orderByList.add(orderByVisitor.getOrderBy());
      }

      projection.setOrderBy(orderByList);
    }


    setLimitOffset(projection, plainSelect);

    relation = projection;
  }


  private void setLimitOffset(Projection projection, PlainSelect plainSelect) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    if (plainSelect.getTop() != null) {
      expressionVisitor.reset();
      plainSelect.getTop().getExpression().accept(expressionVisitor);
      projection.setLimit(expressionVisitor.getExpression());
    }

    if (plainSelect.getLimit() != null) {

      if (plainSelect.getLimit().getOffset() != null) {
        expressionVisitor.reset();
        plainSelect.getLimit().getOffset().accept(expressionVisitor);
        projection.setOffset(expressionVisitor.getExpression());
      }

      if (plainSelect.getLimit().getRowCount() != null) {
        expressionVisitor.reset();
        plainSelect.getLimit().getRowCount().accept(expressionVisitor);
        projection.setLimit(expressionVisitor.getExpression());
      }
    }
  }

  private RAJoin createJoin(Join join, Relation leftInput) {
    FromVisitor fromVisitor = new FromVisitor();
    join.getRightItem().accept(fromVisitor);

    RAJoin raJoin;

    if (join.isLeft()) {
      raJoin = new OuterJoin(leftInput, fromVisitor.getInputRelation(), OuterJoin.Direction
          .LEFT);
    } else if (join.isRight()) {
      raJoin = new OuterJoin(leftInput, fromVisitor.getInputRelation(), OuterJoin.Direction
          .RIGHT);
    } else if (join.isInner()) {
      raJoin = new InnerJoin(leftInput, fromVisitor.getInputRelation());
    } else if (join.isFull()) {
      raJoin = new FullJoin(leftInput, fromVisitor.getInputRelation());
    } else {
      throw new RuntimeException("Could not determine join type. Not implemented");
    }

    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    join.getOnExpression().accept(expressionVisitor);
    raJoin.setOnExpression(expressionVisitor.getExpression());

    return raJoin;
  }

  @Override
  public void visit(SetOperationList setOpList) {
    if (setOpList.getSelects().size() != 2) {
      throw new RuntimeException("Jaap check this out");
    }

    if (setOpList.getOperations().size() > 1) {
      throw new RuntimeException("Jaap check this out");
    }
    SelectVisitor selectVisitor = new SelectVisitor();

    setOpList.getSelects().get(0).accept(selectVisitor);
    Relation leftRelation = selectVisitor.getRelation();
    setOpList.getSelects().get(1).accept(selectVisitor);
    Relation rightRelation = selectVisitor.getRelation();

    String operation = setOpList.getOperations().get(0).toString();
    if (operation.startsWith("UNION")) {
      relation = new Union(leftRelation, rightRelation, operation.contains("ALL"));
    } else {
      throw new RuntimeException("Unimplemented operation");
    }
  }

  @Override
  public void visit(WithItem withItem) {
    super.visit(withItem);
  }


}
