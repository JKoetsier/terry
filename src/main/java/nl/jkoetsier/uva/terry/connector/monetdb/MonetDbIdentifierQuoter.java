package nl.jkoetsier.uva.terry.connector.monetdb;

import nl.jkoetsier.uva.terry.connector.SqlIdentifierQuoter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonetDbIdentifierQuoter extends SqlIdentifierQuoter {

  private static Logger logger = LoggerFactory.getLogger(MonetDbIdentifierQuoter.class);

  @Override
  public char getOpenChar() {
    return '"';
  }

  @Override
  public char getCloseChar() {
    return '"';
  }
}
