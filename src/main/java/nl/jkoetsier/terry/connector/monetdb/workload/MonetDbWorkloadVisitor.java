package nl.jkoetsier.terry.connector.monetdb.workload;

import nl.jkoetsier.terry.connector.SqlIdentifierQuoter;
import nl.jkoetsier.terry.connector.monetdb.MonetDbIdentifierQuoter;
import nl.jkoetsier.terry.intrep.workload.expression.FunctionExpr;
import nl.jkoetsier.terry.connector.SqlWorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonetDbWorkloadVisitor extends SqlWorkloadVisitor {

  static Logger logger = LoggerFactory.getLogger(MonetDbWorkloadVisitor.class);

  @Override
  protected SqlIdentifierQuoter getIdentifierQuoter() {
    return new MonetDbIdentifierQuoter();
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
