package nl.jkoetsier.uva.dbbench.connector.mysql.workload;

import nl.jkoetsier.uva.dbbench.connector.SqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FullJoin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlWorkloadVisitor extends SqlWorkloadVisitor {

  private static Logger logger = LoggerFactory.getLogger(MySqlWorkloadVisitor.class);

  @Override
  public void visit(FullJoin fullJoin) {
    throw new RuntimeException("Not supported in MySQL. Do something with this");
  }
}
