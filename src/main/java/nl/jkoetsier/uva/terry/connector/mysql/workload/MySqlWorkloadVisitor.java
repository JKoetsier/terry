package nl.jkoetsier.uva.terry.connector.mysql.workload;

import nl.jkoetsier.uva.terry.connector.SqlIdentifierQuoter;
import nl.jkoetsier.uva.terry.connector.SqlWorkloadVisitor;
import nl.jkoetsier.uva.terry.connector.mysql.MySqlIdentifierQuoter;
import nl.jkoetsier.uva.terry.internal.workload.expression.FunctionExpr;
import nl.jkoetsier.uva.terry.internal.workload.query.FullJoin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlWorkloadVisitor extends SqlWorkloadVisitor {

  private static Logger logger = LoggerFactory.getLogger(MySqlWorkloadVisitor.class);

  @Override
  protected SqlIdentifierQuoter getIdentifierQuoter() {
    return new MySqlIdentifierQuoter();
  }

  @Override
  public void visit(FullJoin fullJoin) {
    supported = false;
    super.visit(fullJoin);
  }

  @Override
  public void visit(FunctionExpr functionExpr) {
    String arguments = currentStack.pop();

    // Replace SQL Server ISNULL with IFNULL
    String functionName =
        functionExpr.getName().toLowerCase().equals("isnull") ? "IFNULL" : functionExpr.getName();

    currentStack.push(String.format("%s(%s)", functionName, arguments));
  }
}
