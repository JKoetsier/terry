package nl.jkoetsier.terry.intrep.workload.query;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJoin extends RAJoin {

  private static Logger logger = LoggerFactory.getLogger(SimpleJoin.class);

  public SimpleJoin(Relation leftInput, Relation rightInput) {
    super(leftInput, rightInput);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    leftInput.acceptVisitor(workloadVisitor);
    rightInput.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "InnerJoin{" +
        "leftInput=" + leftInput +
        ", rightInput=" + rightInput +
        '}';
  }

}
