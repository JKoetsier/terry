package nl.jkoetsier.uva.dbbench.connector.monetdb.workload;

import nl.jkoetsier.uva.dbbench.connector.SqlWorkloadVisitor;
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
}
