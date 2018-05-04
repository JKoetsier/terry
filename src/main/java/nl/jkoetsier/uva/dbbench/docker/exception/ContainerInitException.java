package nl.jkoetsier.uva.dbbench.docker.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContainerInitException extends RuntimeException {

  private static Logger logger = LoggerFactory.getLogger(ContainerInitException.class);

  public ContainerInitException(String message) {
    super(message);
  }
}
