package nl.jkoetsier.uva.dbbench.workload.relalg;

public abstract class BinaryRelation extends Relation {

    private Relation leftHand;
    private Relation rightHand;

    public Relation getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Relation leftHand) {
        this.leftHand = leftHand;
    }

    public Relation getRightHand() {
        return rightHand;
    }

    public void setRightHand(Relation rightHand) {
        this.rightHand = rightHand;
    }
}
