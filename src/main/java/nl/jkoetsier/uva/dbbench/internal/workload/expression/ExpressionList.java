package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import java.util.List;
import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class ExpressionList extends Expression {

  private List<Expression> expressions;


  public ExpressionList(
      List<Expression> expressions) {
    this.expressions = expressions;
  }

  @Override
  public void validate(Schema schema, Relation relation) throws NotMatchingWorkloadException {

  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    for (Expression expression : expressions) {
      expression.acceptVisitor(workloadVisitor);
    }

    workloadVisitor.visit(this);
  }
}
