package nl.jkoetsier.uva.terry.connector.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnsupportedConfigurationException extends DatabaseException {

  private static Logger logger = LoggerFactory.getLogger(UnsupportedConfigurationException.class);

  public UnsupportedConfigurationException(String message) {
    super(message);
  }

  public UnsupportedConfigurationException(Throwable cause) {
    super(cause);
  }
}
