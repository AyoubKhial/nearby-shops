package com.unitedremote.codingchallenge.shopsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * thrown each time we send a request to a not exist endpoint.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with {%s : '%s'}", resourceName, fieldName, fieldValue));
    }
}