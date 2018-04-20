package nl.jkoetsier.uva.dbbench.internal.workload.visitor;

public interface WorkloadTreeElement {

  void acceptVisitor(WorkloadVisitor workloadVisitor);
}
