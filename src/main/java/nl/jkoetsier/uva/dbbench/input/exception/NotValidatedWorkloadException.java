package nl.jkoetsier.uva.dbbench.input.exception;

public class NotValidatedWorkloadException extends RuntimeException {

  public NotValidatedWorkloadException(String message) {
    super(message);
  }

  public NotValidatedWorkloadException() {
    super("Action not possible on not-validated workload");
  }
}
