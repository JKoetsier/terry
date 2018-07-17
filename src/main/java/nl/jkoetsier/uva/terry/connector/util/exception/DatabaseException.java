package nl.jkoetsier.uva.terry.connector.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseException extends Exception {

  private static Logger logger = LoggerFactory.getLogger(DatabaseException.class);

  public DatabaseException(Throwable cause) {
    super(cause);
  }
}