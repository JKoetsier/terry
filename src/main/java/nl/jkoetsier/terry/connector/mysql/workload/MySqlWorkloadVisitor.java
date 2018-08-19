package nl.jkoetsier.terry.connector.mysql.workload;

import nl.jkoetsier.terry.connector.SqlIdentifierQuoter;
import nl.jkoetsier.terry.connector.mysql.MySqlIdentifierQuoter;
import nl.jkoetsier.terry.intrep.workload.expression.FunctionExpr;
import nl.jkoetsier.terry.intrep.workload.query.FullJoin;
import nl.jkoetsier.terry.connector.SqlWorkloadVisitor;
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
