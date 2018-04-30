package nl.jkoetsier.uva.dbbench.internal.workload.visitor;

public interface WorkloadElement {

  void acceptVisitor(WorkloadVisitor workloadVisitor);
}
