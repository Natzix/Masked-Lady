package org.natzi.maskedlady.config.advice;

import jakarta.persistence.EntityNotFoundException;
import org.natzi.maskedlady.config.exception.InvalidPersistenceException;
import org.natzi.maskedlady.config.exception.PasswordInvalidException;
import org.natzi.maskedlady.utils.dto.ResponseExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseExceptionDTO> handleExceptionNotFound(EntityNotFoundException ex) {
        ResponseExceptionDTO error = new ResponseExceptionDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPersistenceException.class)
    public ResponseEntity<ResponseExceptionDTO> handleExceptionFound(InvalidPersistenceException ex) {
        ResponseExceptionDTO error = new ResponseExceptionDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
