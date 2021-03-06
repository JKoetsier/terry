package nl.jkoetsier.terry.connector.mssql;

import nl.jkoetsier.terry.connector.SqlIdentifierQuoter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsSqlIdentifierQuoter extends SqlIdentifierQuoter {

  private static Logger logger = LoggerFactory.getLogger(MsSqlIdentifierQuoter.class);

  @Override
  public char getOpenChar() {
    return '[';
  }

  @Override
  public char getCloseChar() {
    return ']';
  }
}
