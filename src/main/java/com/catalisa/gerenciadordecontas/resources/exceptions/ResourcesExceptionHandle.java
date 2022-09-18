package com.catalisa.gerenciadordecontas.resources.exceptions;

import com.catalisa.gerenciadordecontas.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class ResourcesExceptionHandle {

    @ExceptionHandler(ObjectNotFoundException.class)

    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError erre = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erre);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleConstraint(ConstraintViolationException e) {
        Stream<String> listaDeErros = e.getConstraintViolations().stream().map(violation -> violation.getPropertyPath() + " : " + violation.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaDeErros.collect(Collectors.toList()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handlerMethodArgument(MethodArgumentNotValidException methodArgumentNotValidException) {
        Stream<String> listaDeErros = methodArgumentNotValidException.getBindingResult().getAllErrors().stream().map(error -> ((FieldError) error).getField() + " : " + error.getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaDeErros.collect(Collectors.toList()));
    }

}

