package nl.jkoetsier.uva.terry.connector.postgres.workload;

import nl.jkoetsier.uva.terry.connector.SqlIdentifierQuoter;
import nl.jkoetsier.uva.terry.connector.SqlWorkloadVisitor;
import nl.jkoetsier.uva.terry.connector.postgres.PostgresIdentifierQuoter;
import nl.jkoetsier.uva.terry.intrep.workload.expression.FunctionExpr;
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
