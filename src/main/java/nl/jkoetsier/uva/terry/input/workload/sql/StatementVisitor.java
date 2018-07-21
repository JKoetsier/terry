package nl.jkoetsier.uva.terry.input.workload.sql;

import java.util.ArrayList;
import java.util.List;
import net.sf.jsqlparser.statement.StatementVisitorAdapter;
import net.sf.jsqlparser.statement.select.Select;
import nl.jkoetsier.uva.terry.intrep.workload.Query;

public class StatementVisitor extends StatementVisitorAdapter {

  private List<Query> queries = new ArrayList<>();

  public List<Query> getQueries() {
    return queries;
  }

  @Override
  public void visit(Select select) {
    SelectVisitor selectVisitor = new SelectVisitor();
    select.getSelectBody().accept(selectVisitor);

    queries.add(new Query(selectVisitor.getRelation()));
  }
}
