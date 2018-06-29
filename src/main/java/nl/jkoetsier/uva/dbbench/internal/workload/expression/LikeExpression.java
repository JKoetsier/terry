package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LikeExpression extends Expression {

  private static Logger logger = LoggerFactory.getLogger(LikeExpression.class);

  private Expression subjectExpr;
  private Expression likeExpr;

  public LikeExpression(Expression subjectExpr, Expression likeExpr, boolean not) {
    super(not);
    this.subjectExpr = subjectExpr;
    this.likeExpr = likeExpr;
  }

  public LikeExpression(Expression subjectExpr, Expression likeExpr) {
    this.subjectExpr = subjectExpr;
    this.likeExpr = likeExpr;
  }

  public Expression getSubjectExpr() {
    return subjectExpr;
  }

  public void setSubjectExpr(Expression subjectExpr) {
    this.subjectExpr = subjectExpr;
  }

  public Expression getLikeExpr() {
    return likeExpr;
  }

  public void setLikeExpr(Expression likeExpr) {
    this.likeExpr = likeExpr;
  }

  @Override
  public void validate(ExposedFields exposedFields) {

  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    subjectExpr.acceptVisitor(workloadVisitor);
    likeExpr.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }
}
