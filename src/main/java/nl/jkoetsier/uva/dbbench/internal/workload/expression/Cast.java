package nl.jkoetsier.uva.dbbench.internal.workload.expression;


import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class Cast extends Expression {

  private Expression expression;
  private String type;

  public Cast(Expression expression, String type) {
    this.expression = expression;
    this.type = type;
  }

  public Expression getExpression() {
    return expression;
  }

  public void setExpression(Expression expression) {
    this.expression = expression;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public void validate(Schema schema, Relation relation) throws NotMatchingWorkloadException {

  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {

  }
}
