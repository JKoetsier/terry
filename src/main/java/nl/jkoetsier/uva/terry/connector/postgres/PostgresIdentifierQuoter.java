package nl.jkoetsier.uva.terry.connector.postgres;

import nl.jkoetsier.uva.terry.connector.SqlIdentifierQuoter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresIdentifierQuoter extends SqlIdentifierQuoter {

  private static Logger logger = LoggerFactory.getLogger(PostgresIdentifierQuoter.class);

  @Override
  public char getOpenChar() {
    return '"';
  }

  @Override
  public char getCloseChar() {
    return '"';
  }
}
