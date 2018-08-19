package nl.jkoetsier.terry.intrep.workload.expression;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetweenExpression extends Expression {

  private static Logger logger = LoggerFactory.getLogger(BetweenExpression.class);

  private Expression leftBetweenExpr;
  private Expression rightBetweenExpr;
  private Expression subjectExpr;

  public BetweenExpression(Expression subjectExpr, Expression leftBetweenExpr,
      Expression rightBetweenExpr, boolean not) {
    super(not);
    this.subjectExpr = subjectExpr;
    this.leftBetweenExpr = leftBetweenExpr;
    this.rightBetweenExpr = rightBetweenExpr;
  }

  public BetweenExpression(Expression subjectExpr, Expression leftBetweenExpr,
      Expression rightBetweenExpr) {
    this.subjectExpr = subjectExpr;
    this.leftBetweenExpr = leftBetweenExpr;
    this.rightBetweenExpr = rightBetweenExpr;
  }

  public Expression getSubjectExpr() {
    return subjectExpr;
  }

  public Expression getLeftBetweenExpr() {
    return leftBetweenExpr;
  }

  public void setLeftBetweenExpr(Expression leftBetweenExpr) {
    this.leftBetweenExpr = leftBetweenExpr;
  }

  public Expression getRightBetweenExpr() {
    return rightBetweenExpr;
  }

  public void setRightBetweenExpr(Expression rightBetweenExpr) {
    this.rightBetweenExpr = rightBetweenExpr;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    subjectExpr.acceptVisitor(workloadVisitor);
    leftBetweenExpr.acceptVisitor(workloadVisitor);
    rightBetweenExpr.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }
}
