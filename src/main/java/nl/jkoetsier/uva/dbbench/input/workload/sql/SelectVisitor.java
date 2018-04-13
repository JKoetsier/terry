package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.statement.select.*;
import nl.jkoetsier.uva.dbbench.workload.Query;

public class SelectVisitor extends SelectVisitorAdapter {

    private Query query;

    public Query getQuery() {
        return query;
    }

    @Override
    public void visit(PlainSelect plainSelect) {
        query = new Query();

        System.out.println("Have plainselect");

        System.out.println("Top: " + plainSelect.getTop());

        System.out.println(plainSelect.getSelectItems());

        SelectItemVisitor selectItemVisitor = new SelectItemVisitor();

        for (SelectItem selectItem : plainSelect.getSelectItems()) {
            selectItem.accept(selectItemVisitor);
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
