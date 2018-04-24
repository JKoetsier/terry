package nl.jkoetsier.uva.dbbench.input.workload.sql;

import java.util.ArrayList;
import java.util.List;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitorAdapter;
import nl.jkoetsier.uva.dbbench.input.exception.InvalidQueryException;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FieldRef;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FieldRefs;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Projection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Selection;

public class SelectItemVisitor extends SelectItemVisitorAdapter {

  private Selection selection;
  private List<String> selectColumns = new ArrayList<>();
  private Relation returnRelation;

  public SelectItemVisitor(Selection selection) {
    this.selection = selection;
    this.returnRelation = selection;
  }

  public Relation getRelation() {
    if (selectColumns.size() > 0) {
      Projection projection = new Projection(selectColumns);
      projection.setInput(selection);

      return projection;
    }

    return returnRelation;
  }

  @Override
  public void visit(AllColumns columns) {
    // Do nothing, no projection needed
    super.visit(columns);
  }

  @Override
  public void visit(AllTableColumns columns) {
    Projection projection = new Projection(columns.getTable().getName());
    projection.setInput(selection);
    returnRelation = projection;
  }

  @Override
  public void visit(SelectExpressionItem item) {
    selectColumns.add(item.toString());
  }
}
