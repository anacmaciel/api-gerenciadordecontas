package com.catalisa.gerenciadordecontas.resources.exceptions;

import com.catalisa.gerenciadordecontas.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourcesExceptionHandle {

    @ExceptionHandler(ObjectNotFoundException.class)

    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError erre = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erre);
    }
}
