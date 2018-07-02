package nl.jkoetsier.uva.terry.internal.workload.visitor;

public interface WorkloadElement {

  void acceptVisitor(WorkloadVisitor workloadVisitor);
}
