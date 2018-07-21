package nl.jkoetsier.uva.terry.intrep.workload.visitor;

public interface WorkloadElement {

  void acceptVisitor(WorkloadVisitor workloadVisitor);
}
