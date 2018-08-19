package nl.jkoetsier.terry.intrep.workload.visitor;

public interface WorkloadElement {

  void acceptVisitor(WorkloadVisitor workloadVisitor);
}
