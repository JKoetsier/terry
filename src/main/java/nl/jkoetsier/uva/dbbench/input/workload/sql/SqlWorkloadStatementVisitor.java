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
  public void visit(Select select) {
    SelectVisitor selectVisitor = new SelectVisitor();
    select.getSelectBody().accept(selectVisitor);

    workload.addQuery(selectVisitor.getQuery());
  }
}
