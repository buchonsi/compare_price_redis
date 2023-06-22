package com.example.pricecompareredis.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Keys in Redis")
public class NotFoundException extends RuntimeException{
    private String errorMsg;
    private HttpStatus httpStatus;

    public NotFoundException(String errorMsg, HttpStatus httpStatus) {
        this.errorMsg = errorMsg;
        this.httpStatus = httpStatus;
    }
}
