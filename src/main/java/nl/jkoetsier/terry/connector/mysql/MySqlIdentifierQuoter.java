package nl.jkoetsier.terry.connector.mysql;

import nl.jkoetsier.terry.connector.SqlIdentifierQuoter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlIdentifierQuoter extends SqlIdentifierQuoter {

  private static Logger logger = LoggerFactory.getLogger(MySqlIdentifierQuoter.class);

  @Override
  public char getOpenChar() {
    return '`';
  }

  @Override
  public char getCloseChar() {
    return '`';
  }
}
