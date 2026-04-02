package org.natzi.maskedlady.config.advice;

import com.nimbusds.jose.JOSEException;
import org.natzi.maskedlady.config.exception.NimbusJoseException;
import org.natzi.maskedlady.utils.dto.ResponseExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JoseJwtExceptionHandler {


    @ExceptionHandler(NimbusJoseException.class)
    public ResponseEntity<ResponseExceptionDTO> handleExceptionBuild(NimbusJoseException ex) {
        ResponseExceptionDTO error = new ResponseExceptionDTO(HttpStatus.BAD_GATEWAY.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
    }
}
