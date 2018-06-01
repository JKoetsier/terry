package nl.jkoetsier.uva.dbbench.connector.mysql.workload;

import nl.jkoetsier.uva.dbbench.connector.SqlIdentifierQuoter;
import nl.jkoetsier.uva.dbbench.connector.SqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.connector.mysql.MySqlIdentifierQuoter;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FunctionExpr;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FullJoin;
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
    throw new RuntimeException("Not supported in MySQL. Do something with this");
  }

  @Override
  public void visit(FunctionExpr functionExpr) {
    logger.debug("Visit FunctionExpr");

    String arguments = currentStack.pop();

    // Replace SQL Server ISNULL with IFNULL
    String functionName = functionExpr.getName().toLowerCase().equals("isnull") ? "IFNULL" : functionExpr.getName();

    currentStack.push(String.format("%s(%s)", functionName, arguments));
  }
}
