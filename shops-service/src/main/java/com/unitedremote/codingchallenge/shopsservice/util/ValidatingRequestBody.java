package com.unitedremote.codingchallenge.shopsservice.util;

import com.unitedremote.codingchallenge.shopsservice.exception.BadRequestException;
import org.apache.commons.validator.GenericValidator;

/**
 * This interface contains methods that validate the request body.
 */
public interface ValidatingRequestBody {

    static void keyValueShouldNotBeNull(String keyValue, String keyName) {
        if(null == keyValue) {
            throw new BadRequestException(keyName + " must be set.");
        }
    }

    static void keyValueLengthShouldBeBetween(String keyValue, int minSize, int maxSize, String keyName) {
        if(!GenericValidator.minLength(keyValue, minSize) || !GenericValidator.maxLength(keyValue, maxSize)) {
            throw new BadRequestException(keyName + " should contain " + minSize + " to " + maxSize + " character.");
        }
    }

    static void keyValueShouldBeEmail(String keyValue) {
        if(!GenericValidator.isEmail(keyValue)) {
            throw new BadRequestException("Please enter a valid email.");
        }
    }
}