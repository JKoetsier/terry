package nl.jkoetsier.uva.dbbench.connector.monetdb.workload;

import nl.jkoetsier.uva.dbbench.connector.SqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FunctionExpr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonetDbWorkloadVisitor extends SqlWorkloadVisitor {

  static Logger logger = LoggerFactory.getLogger(MonetDbWorkloadVisitor.class);

  @Override
  protected char getQuoteCharOpen() {
    return '"';
  }

  @Override
  protected char getQuoteCharClose() {
    return '"';
  }

  @Override
  public void visit(FunctionExpr functionExpr) {
    logger.debug("Visit FunctionExpr");

    String arguments = currentStack.pop();

    // Replace SQL Server ISNULL with COALESCE
    String functionName = functionExpr.getName().toLowerCase().equals("isnull") ? "COALESCE" : functionExpr.getName();

    currentStack.push(String.format("%s(%s)", functionName, arguments));
  }
}
