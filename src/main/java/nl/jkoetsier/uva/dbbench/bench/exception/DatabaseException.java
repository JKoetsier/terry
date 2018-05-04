package nl.jkoetsier.uva.dbbench.bench.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseException extends Exception {

  private static Logger logger = LoggerFactory.getLogger(DatabaseException.class);

  public DatabaseException(Throwable cause) {
    super(cause);
  }
}
