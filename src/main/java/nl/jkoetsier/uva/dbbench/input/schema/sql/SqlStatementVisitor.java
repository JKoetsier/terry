package nl.jkoetsier.uva.dbbench.input.schema.sql;


import net.sf.jsqlparser.statement.Commit;
import net.sf.jsqlparser.statement.SetStatement;
import net.sf.jsqlparser.statement.StatementVisitorAdapter;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.create.index.CreateIndex;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
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
import nl.jkoetsier.uva.dbbench.datamodel.DataModel;
import nl.jkoetsier.uva.dbbench.datamodel.Entity;
import nl.jkoetsier.uva.dbbench.datamodel.fields.Field;
import nl.jkoetsier.uva.dbbench.datamodel.fields.FieldFactory;

public class SqlStatementVisitor extends StatementVisitorAdapter {

    private DataModel dataModel = new DataModel();

    public DataModel getDataModel() {
        return dataModel;
    }

    @Override
    public void visit(Commit commit) {
        super.visit(commit);
    }

    @Override
    public void visit(Select select) {

        super.visit(select);
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
        Entity entity = new Entity(createTable.getTable().getName());

        for (ColumnDefinition colDef : createTable.getColumnDefinitions()) {
            Field field = FieldFactory.create(colDef.getColDataType().getDataType());
            field.setName(colDef.getColumnName());
            entity.addField(field);
        }

        dataModel.addEntity(entity);

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
}
