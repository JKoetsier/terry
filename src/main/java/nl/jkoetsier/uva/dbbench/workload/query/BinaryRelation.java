package nl.jkoetsier.uva.dbbench.workload.query;

public abstract class BinaryRelation extends Relation {

  private Relation leftInput;
  private Relation rightInput;

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
