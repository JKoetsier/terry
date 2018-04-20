package nl.jkoetsier.uva.dbbench.internal.workload.query;

public abstract class UnaryRelation extends Relation {

  protected Relation input;

  public Relation getInput() {
    return input;
  }

  public void setInput(Relation input) {
    this.input = input;
  }
}
