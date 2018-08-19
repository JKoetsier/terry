package nl.jkoetsier.terry.intrep.workload.query;

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
