package nl.jkoetsier.uva.dbbench.internal.workload.query;

import java.util.HashMap;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.internal.schema.Table;

public abstract class BinaryRelation extends Relation {

  protected Relation leftInput;
  protected Relation rightInput;

  public BinaryRelation(Relation leftInput, Relation rightInput) {
    this.leftInput = leftInput;
    this.rightInput = rightInput;
  }

  public Relation getLeftInput() {
    return leftInput;
  }

  public void setLeftInput(Relation leftInput) {
    this.leftInput = leftInput;
  }

  public Relation getRightInput() {
    return rightInput;
  }

  public void setRightInput(Relation rightInput) {
    this.rightInput = rightInput;
  }

  @Override
  public HashMap<Table, Integer> getTouchedTables() {
    return addMaps(leftInput.getTouchedTables(), rightInput.getTouchedTables());
  }

  static HashMap<Table, Integer> addMaps(HashMap<Table, Integer> mapA, HashMap<Table, Integer> mapB) {
    HashMap<Table, Integer> result = new HashMap<>(mapA);

    for (Entry<Table, Integer> entry : mapB.entrySet()) {
      if (result.containsKey(entry.getKey())) {
        result.put(entry.getKey(), result.get(entry.getKey()) + entry.getValue());
      } else {
        result.put(entry.getKey(), entry.getValue());
      }
    }

    return result;
  }
}
