package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.statement.StatementVisitorAdapter;
import net.sf.jsqlparser.statement.select.Select;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;

public class StatementVisitor extends StatementVisitorAdapter {

  private Workload workload = new Workload();

  public Workload getWorkload() {
    return workload;
  }

  @Override
  public void visit(Select select) {
    SelectVisitor selectVisitor = new SelectVisitor();
    select.getSelectBody().accept(selectVisitor);

    workload.addQuery(new Query(selectVisitor.getRelation()));
  }
}
