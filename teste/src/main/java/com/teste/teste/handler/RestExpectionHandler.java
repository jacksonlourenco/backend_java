package com.teste.teste.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teste.teste.model.error.ErrorMessage;
import com.teste.teste.model.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExpectionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {

        ErrorMessage error = new ErrorMessage("Not Found", 404, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
