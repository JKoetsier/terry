package nl.jkoetsier.uva.dbbench.workload.visitor;

public interface WorkloadTreeElement {

  void acceptVisitor(WorkloadVisitor workloadVisitor);
}
