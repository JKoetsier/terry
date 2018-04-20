package nl.jkoetsier.uva.dbbench.workload.query;

public abstract class UnaryRelation extends Relation {

  protected Relation input;

  public Relation getInput() {
    return input;
  }

  public void setInput(Relation input) {
    this.input = input;
  }
}
