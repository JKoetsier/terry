package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItemVisitorAdapter;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.TableFunction;
import net.sf.jsqlparser.statement.select.ValuesList;
import nl.jkoetsier.uva.dbbench.internal.workload.query.InputRelation;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;

public class FromVisitor extends FromItemVisitorAdapter {

  private Relation relation;

  public FromVisitor() {
  }

  public Relation getInputRelation() {
    return relation;
  }

  @Override
  public void visit(Table table) {
    if (table.getAlias() == null) {
      this.relation = new InputRelation(table.getName());
    } else {
      this.relation = new InputRelation(table.getName(), table.getAlias().getName());
    }
  }

  @Override
  public void visit(SubSelect subSelect) {
    SelectVisitor selectVisitor = new SelectVisitor();

    subSelect.getSelectBody().accept(selectVisitor);

    this.relation = selectVisitor.getRelation();
  }

  @Override
  public void visit(SubJoin subjoin) {
    super.visit(subjoin);
  }

  @Override
  public void visit(LateralSubSelect lateralSubSelect) {
    super.visit(lateralSubSelect);
  }

  @Override
  public void visit(ValuesList valuesList) {
    super.visit(valuesList);
  }

  @Override
  public void visit(TableFunction valuesList) {
    super.visit(valuesList);
  }
}
