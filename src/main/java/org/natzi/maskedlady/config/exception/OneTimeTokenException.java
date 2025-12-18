package org.natzi.maskedlady.config.exception;

public class OneTimeTokenException extends RuntimeException {

    public OneTimeTokenException() {
        super();
    }

    public OneTimeTokenException(String message) {
        super(message);
    }

    public OneTimeTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
