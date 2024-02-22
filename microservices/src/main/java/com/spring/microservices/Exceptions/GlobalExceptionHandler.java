package com.spring.microservices.Exceptions;

import com.spring.microservices.Payloads.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Exception> handleResourceNotFoundException(ResourceNotFoundException ex){
          String message = ex.getMessage();
          Exception exception = new Exception();
          exception.setMessage(message);
          exception.setStatus(HttpStatus.NOT_FOUND);
          exception.setSuccess(true);
          return new ResponseEntity<Exception>(exception,HttpStatus.NOT_FOUND);
    }
}
