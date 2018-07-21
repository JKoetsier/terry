package nl.jkoetsier.uva.terry.intrep.workload.expression;

import java.util.List;
import nl.jkoetsier.uva.terry.intrep.workload.query.ExposedFields;
import nl.jkoetsier.uva.terry.intrep.workload.visitor.WorkloadVisitor;

public class ExpressionList extends Expression {

  private List<Expression> expressions;

  public ExpressionList(
      List<Expression> expressions) {
    this.expressions = expressions;
  }

  public List<Expression> getExpressions() {
    return expressions;
  }

  @Override
  public ExposedFields getExposedFields() {
    ExposedFields exposedFields = new ExposedFields();

    for (Expression expression : expressions) {
      exposedFields.addAll(expression.getExposedFields());
    }

    return exposedFields;
  }

  @Override
  public void validate(ExposedFields exposedFields) {
    for (Expression expression : expressions) {
      expression.validate(exposedFields);
    }
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    for (Expression expression : expressions) {
      expression.acceptVisitor(workloadVisitor);
    }

    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "ExpressionList{" +
        "expressions=" + expressions +
        '}';
  }
}
