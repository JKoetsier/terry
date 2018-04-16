package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.statement.select.*;
import nl.jkoetsier.uva.dbbench.workload.Query;
import nl.jkoetsier.uva.dbbench.workload.query.*;

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
        System.out.println("Have setopselect");
        super.visit(setOpList);
    }

    @Override
    public void visit(WithItem withItem) {
        System.out.println("Have withitem");
        super.visit(withItem);
    }


}
