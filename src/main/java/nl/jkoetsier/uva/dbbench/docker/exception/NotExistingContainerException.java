package nl.jkoetsier.uva.dbbench.docker.exception;

public class NotExistingContainerException extends Exception {

  public NotExistingContainerException(String message) {
    super(message);
  }
}
