package nl.jkoetsier.uva.dbbench.internal.workload.query;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.internal.schema.Table;

public abstract class UnaryRelation extends Relation {

  protected Relation input;

  public Relation getInput() {
    return input;
  }

  public void setInput(Relation input) {
    this.input = input;
  }

  @Override
  public HashMap<Table, Integer> getTouchedTables() {
    return input.getTouchedTables();
  }
}
