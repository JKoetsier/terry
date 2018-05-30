package nl.jkoetsier.uva.dbbench.connector.postgres.workload;

import nl.jkoetsier.uva.dbbench.connector.SqlWorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresWorkloadVisitor extends SqlWorkloadVisitor {

  private static Logger logger = LoggerFactory.getLogger(PostgresWorkloadVisitor.class);

  @Override
  protected char getQuoteCharOpen() {
    return '"';
  }

  @Override
  protected char getQuoteCharClose() {
    return '"';
  }
}
