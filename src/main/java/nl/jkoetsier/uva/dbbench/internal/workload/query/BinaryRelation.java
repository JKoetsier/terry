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
}
