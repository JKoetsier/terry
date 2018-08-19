package nl.jkoetsier.terry.connector.postgres.workload;

import nl.jkoetsier.terry.connector.SqlIdentifierQuoter;
import nl.jkoetsier.terry.intrep.workload.expression.FunctionExpr;
import nl.jkoetsier.terry.connector.SqlWorkloadVisitor;
import nl.jkoetsier.terry.connector.postgres.PostgresIdentifierQuoter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresWorkloadVisitor extends SqlWorkloadVisitor {

  private static Logger logger = LoggerFactory.getLogger(PostgresWorkloadVisitor.class);


  @Override
  protected SqlIdentifierQuoter getIdentifierQuoter() {
    return new PostgresIdentifierQuoter();
  }

  @Override
  public void visit(FunctionExpr functionExpr) {
    String arguments = currentStack.pop();

    // Replace SQL Server ISNULL with COALESCE
    String functionName =
        functionExpr.getName().toLowerCase().equals("isnull") ? "COALESCE" : functionExpr.getName();

    currentStack.push(String.format("%s(%s)", functionName, arguments));
  }
}
