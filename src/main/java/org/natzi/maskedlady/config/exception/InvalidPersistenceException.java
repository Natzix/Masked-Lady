package org.natzi.maskedlady.config.exception;

public class InvalidPersistenceException extends RuntimeException {

    public InvalidPersistenceException() {
    }

    public InvalidPersistenceException(String message) {
        super(message);
    }

    public InvalidPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
