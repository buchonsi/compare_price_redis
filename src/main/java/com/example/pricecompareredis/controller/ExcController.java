package com.example.pricecompareredis.controller;

import com.example.pricecompareredis.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcController {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> everyException(final Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> notFoundExceptionResponse(NotFoundException exception) {
        return new ResponseEntity<>(exception.getErrorMsg(), exception.getHttpStatus());
    }
}
