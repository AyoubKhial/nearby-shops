package com.unitedremote.codingchallenge.shopsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * thrown each time we send a request with invalid parameter(s)
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid parameters")
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
