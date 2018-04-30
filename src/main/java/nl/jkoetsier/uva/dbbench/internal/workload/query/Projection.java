package nl.jkoetsier.uva.dbbench.internal.workload.query;


import java.util.List;
import nl.jkoetsier.uva.dbbench.internal.workload.element.OrderBy;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Expression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.SelectExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class Projection extends UnaryRelation {

  private String tableName;
  private Expression limit;
  private Expression offset;
  private List<OrderBy> orderBy;
  private List<SelectExpression> selectExpressions;
  private ExposedFields exposedFields;

  public Projection() {
  }

  public Projection(
      List<SelectExpression> selectExpressions) {
    this.selectExpressions = selectExpressions;
  }

  public List<SelectExpression> getSelectExpressions() {
    return selectExpressions;
  }

  public void setSelectExpressions(
      List<SelectExpression> selectExpressions) {
    this.selectExpressions = selectExpressions;
  }

  public Expression getLimit() {
    return limit;
  }

  public void setLimit(Expression limit) {
    this.limit = limit;
  }

  public Expression getOffset() {
    return offset;
  }

  public void setOffset(Expression offset) {
    this.offset = offset;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  @Override
  public ExposedFields getExposedFields() {
    return exposedFields;
  }

  public void setExposedFields(
      ExposedFields exposedFields) {
    this.exposedFields = exposedFields;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    input.acceptVisitor(workloadVisitor);

    if (selectExpressions != null) {
      for (SelectExpression selectExpression : selectExpressions) {
        selectExpression.acceptVisitor(workloadVisitor);
      }
    }

    if (limit != null) {
      limit.acceptVisitor(workloadVisitor);
    }

    workloadVisitor.visit(this);
  }

//  @Override
//  public void validate(Schema schema) throws NotMatchingWorkloadException {
//    input.validate(schema);
//
//    if (fieldRefs != null) {
//      fieldRefs.validate(schema, input);
//    } else if (tableName != null) {
//      // Project all table columns
//      fieldRefs = new FieldRefs();
//      fieldRefs.addAll(input.getFieldRefsForTable(tableName));
//    } else {
//      fieldRefs = input.getFieldRefs();
//    }
//
//    isValidated = true;
//  }

  public List<OrderBy> getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(List<OrderBy> orderBy) {
    this.orderBy = orderBy;
  }
}
