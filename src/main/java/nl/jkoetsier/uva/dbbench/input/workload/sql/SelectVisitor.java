package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.statement.select.*;
import nl.jkoetsier.uva.dbbench.workload.Query;
import nl.jkoetsier.uva.dbbench.workload.query.InputRelation;
import nl.jkoetsier.uva.dbbench.workload.query.RAJoin;
import nl.jkoetsier.uva.dbbench.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.workload.query.Selection;

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

                join.getRightItem().accept(fromVisitor);

                RAJoin raJoin = new RAJoin(
                        selection.getInput(),
                        fromVisitor.getInputRelation()
                );

                ExpressionVisitor expressionVisitor = new ExpressionVisitor(raJoin);
                join.getOnExpression().accept(expressionVisitor);
                raJoin.setOnExpression(expressionVisitor.getExpression());
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
