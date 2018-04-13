package nl.jkoetsier.uva.dbbench.workload.relalg;

public abstract class UnaryRelation extends Relation {

    private Relation input;

    public Relation getInput() {
        return input;
    }

    public void setInput(Relation input) {
        this.input = input;
    }
}
