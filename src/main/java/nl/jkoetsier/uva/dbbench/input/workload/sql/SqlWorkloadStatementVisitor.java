package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.statement.*;
import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.create.index.CreateIndex;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.view.AlterView;
import net.sf.jsqlparser.statement.create.view.CreateView;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.execute.Execute;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.merge.Merge;
import net.sf.jsqlparser.statement.replace.Replace;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.truncate.Truncate;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.upsert.Upsert;
import nl.jkoetsier.uva.dbbench.workload.Workload;

public class SqlWorkloadStatementVisitor extends StatementVisitorAdapter {

    private Workload workload = new Workload();

    public Workload getWorkload() {
        return workload;
    }

    @Override
    public void visit(Commit commit) {
        super.visit(commit);
    }

    @Override
    public void visit(Select select) {
        SelectVisitor selectVisitor = new SelectVisitor();
        select.getSelectBody().accept(selectVisitor);

        workload.addQuery(selectVisitor.getQuery());
    }

    @Override
    public void visit(Delete delete) {
        super.visit(delete);
    }

    @Override
    public void visit(Update update) {
        super.visit(update);
    }

    @Override
    public void visit(Insert insert) {
        super.visit(insert);
    }

    @Override
    public void visit(Replace replace) {
        super.visit(replace);
    }

    @Override
    public void visit(Drop drop) {
        super.visit(drop);
    }

    @Override
    public void visit(Truncate truncate) {
        super.visit(truncate);
    }

    @Override
    public void visit(CreateIndex createIndex) {
        super.visit(createIndex);
    }

    @Override
    public void visit(CreateTable createTable) {
        super.visit(createTable);
    }

    @Override
    public void visit(CreateView createView) {
        super.visit(createView);
    }

    @Override
    public void visit(Alter alter) {
        super.visit(alter);
    }

    @Override
    public void visit(Statements stmts) {
        super.visit(stmts);
    }

    @Override
    public void visit(Execute execute) {
        super.visit(execute);
    }

    @Override
    public void visit(SetStatement set) {
        super.visit(set);
    }

    @Override
    public void visit(Merge merge) {
        super.visit(merge);
    }

    @Override
    public void visit(AlterView alterView) {
        super.visit(alterView);
    }

    @Override
    public void visit(Upsert upsert) {
        super.visit(upsert);
    }

    @Override
    public void visit(UseStatement use) {
        super.visit(use);
    }

}
