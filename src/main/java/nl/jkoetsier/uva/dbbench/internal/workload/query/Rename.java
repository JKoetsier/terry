//package nl.jkoetsier.uva.dbbench.internal.workload.query;
//
//import java.util.List;
//import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
//
//public class Rename extends UnaryRelation {
//
//  @Override
//  public FieldRef getFieldRef(String fieldName) {
//    throw new RuntimeException("Not implemented");
//  }
//
//  @Override
//  public FieldRef getFieldRef(String tableName, String fieldName) {
//    throw new RuntimeException("Not implemented");
//  }
//
//  @Override
//  public List<FieldRef> getFieldRefsForTable(String tableName) {
//    throw new RuntimeException("Not implemented");
//  }
//
//  @Override
//  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
//    workloadVisitor.visit(this);
//  }
//}
