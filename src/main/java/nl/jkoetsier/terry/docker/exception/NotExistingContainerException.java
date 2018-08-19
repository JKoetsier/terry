package nl.jkoetsier.terry.docker.exception;

public class NotExistingContainerException extends Exception {

  public NotExistingContainerException(String message) {
    super(message);
  }
}
