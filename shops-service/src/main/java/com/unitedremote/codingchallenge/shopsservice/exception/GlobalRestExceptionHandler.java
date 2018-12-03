package com.unitedremote.codingchallenge.shopsservice.exception;

import com.unitedremote.codingchallenge.shopsservice.util.HTTPCode;
import com.unitedremote.codingchallenge.shopsservice.util.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * interceptor of exceptions thrown by the controllers.
 * <p>
 * The <code>RestControllerAdvice</code> used to enable a single <code>ExceptionHandler</code> to be applied to multiple
 * controllers. This way we can in just one place define how to treat such an exception and this handler will be called
 * when the exception is thrown from classes that are covered by this ControllerAdvice.
 * @author Ayoub Khial
 * @see RestControllerAdvice
 * @see ResponseEntityExceptionHandler
 */
@RestControllerAdvice()
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    // TODO: Figure out how to set a user message !
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestResponse> badRequest(BadRequestException badRequestException) {
        RestResponse response = new RestResponse();
        response.setStatusCode(HTTPCode.BAD_REQUEST);
        response.setInternalMessage(badRequestException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RestResponse> resourceNotFound(ResourceNotFoundException resourceNotFoundException) {
        RestResponse response = new RestResponse();
        response.setStatusCode(HTTPCode.NOT_FOUND);
        response.setInternalMessage(resourceNotFoundException.getMessage());
        response.setUserMessage("Page not found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}