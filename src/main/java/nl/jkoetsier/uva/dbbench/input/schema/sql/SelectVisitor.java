package nl.jkoetsier.uva.dbbench.input.schema.sql;

import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectVisitorAdapter;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;

public class SelectVisitor extends SelectVisitorAdapter {

    @Override
    public void visit(PlainSelect plainSelect) {
        super.visit(plainSelect);
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
