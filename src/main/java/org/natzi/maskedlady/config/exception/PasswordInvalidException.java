package org.natzi.maskedlady.config.exception;

public class PasswordInvalidException extends RuntimeException {

    public PasswordInvalidException() {
    }

    public PasswordInvalidException(String message) {
        super(message);
    }

    public PasswordInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
