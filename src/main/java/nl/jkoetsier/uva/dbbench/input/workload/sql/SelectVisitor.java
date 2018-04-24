package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SelectVisitorAdapter;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FullJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.InnerJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.OuterJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.RAJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Selection;

public class SelectVisitor extends SelectVisitorAdapter {

  private Query query;

  public Query getQuery() {
    return query;
  }

  @Override
  public void visit(PlainSelect plainSelect) {
    query = new Query();

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

    ExpressionVisitor expressionVisitor = new ExpressionVisitor(selection);

    if (plainSelect.getWhere() != null) {
      plainSelect.getWhere().accept(expressionVisitor);
      selection.setExpression(expressionVisitor.getExpression());
    }

    query.setRelation(selectItemVisitor.getRelation());

    super.visit(plainSelect);
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

    ExpressionVisitor expressionVisitor = new ExpressionVisitor(raJoin);
    join.getOnExpression().accept(expressionVisitor);
    raJoin.setOnExpression(expressionVisitor.getExpression());

    return raJoin;
  }

  @Override
  public void visit(SetOperationList setOpList) {
    super.visit(setOpList);
  }

  @Override
  public void visit(WithItem withItem) {
    super.visit(withItem);
  }


}
