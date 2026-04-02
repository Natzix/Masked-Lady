package org.natzi.maskedlady.config.exception;

import org.springframework.stereotype.Component;

public class NimbusJoseException extends RuntimeException {

    public NimbusJoseException() {
    }

    public NimbusJoseException(String message) {
        super(message);
    }

    public NimbusJoseException(String message, Throwable cause) {
        super(message, cause);
    }
}
