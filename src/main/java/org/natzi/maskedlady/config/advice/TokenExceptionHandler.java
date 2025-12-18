package org.natzi.maskedlady.config.advice;

import org.natzi.maskedlady.config.exception.OneTimeTokenException;
import org.natzi.maskedlady.utils.dto.ResponseExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TokenExceptionHandler {

    @ExceptionHandler(OneTimeTokenException.class)
    public ResponseEntity<ResponseExceptionDTO> handleExceptionToken(OneTimeTokenException ex) {
        ResponseExceptionDTO error = new ResponseExceptionDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
