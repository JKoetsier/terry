package nl.jkoetsier.uva.dbbench.input.exception;

public class InvalidQueryException extends RuntimeException {

    public InvalidQueryException(String message) {
        super(message);
    }
}
