package nl.jkoetsier.uva.dbbench.input.workload.sql;

import java.util.ArrayList;
import java.util.List;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitorAdapter;
import nl.jkoetsier.uva.dbbench.input.exception.InvalidQueryException;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Expression;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FieldRef;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FieldRefs;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Projection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Selection;

public class SelectItemVisitor extends SelectItemVisitorAdapter {

  private Selection selection;
  private List<FieldRef> fieldRefs = new ArrayList<>();
  private Projection returnProjection;

  public SelectItemVisitor(Selection selection) {
    this.selection = selection;
    this.returnProjection = new Projection();
    this.returnProjection.setInput(selection);
  }

  public Projection getRelation() {
    if (fieldRefs.size() > 0) {
      returnProjection.setFieldRefs(new FieldRefs(fieldRefs));
    }

    return returnProjection;
  }

  @Override
  public void visit(AllColumns columns) {
    // Set nothing, project everything
    returnProjection.setInput(selection);
  }

  @Override
  public void visit(AllTableColumns columns) {
    returnProjection.setInput(selection);
    returnProjection.setTableName(columns.getTable().getName());
  }

  @Override
  public void visit(SelectExpressionItem item) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    FieldRef fieldRef = new FieldRef(item.getExpression().toString());
    System.out.println(item.getExpression().toString());
    item.getExpression().accept(expressionVisitor);
    System.out.println(expressionVisitor.getExpression());

    if (item.getAlias() != null) {
      fieldRef.setColumnAlias(item.getAlias().getName());
    }

    fieldRefs.add(fieldRef);
  }
}
