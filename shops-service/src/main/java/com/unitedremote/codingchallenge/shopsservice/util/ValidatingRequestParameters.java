package com.unitedremote.codingchallenge.shopsservice.util;

import com.unitedremote.codingchallenge.shopsservice.exception.BadRequestException;

import static com.unitedremote.codingchallenge.shopsservice.util.ApplicationConstants.MAX_PAGE_SIZE;

/**
 * This class check the validation of the parameters given in each request.
 * @author Ayoub Khial
 */
public interface ValidatingRequestParameters {

    static void parameterShouldBeInteger(String parameterName, String parameterValue) {
        try {
            Integer.parseInt(parameterValue);
        } catch (NumberFormatException ex) {
            throw new BadRequestException(parameterName + " parameter accept integers only, '" + parameterValue + "' is not an integer.");
        }
    }

    static void parameterShouldBeDouble(String parameterName, String parameterValue) {
        try {
            Float.valueOf(parameterValue);
        } catch (NumberFormatException ex) {
            throw new BadRequestException(parameterName + " parameter accept double only, '" + parameterValue + "' is not a double.");
        }
    }

    static void validatePageSizeParameter(String size) {
        int sizeInt = Integer.valueOf(size);
        if(sizeInt > MAX_PAGE_SIZE ) {
            throw new BadRequestException("Page size must not be greater than " + MAX_PAGE_SIZE + ".");
        }
        if(sizeInt < 0) {
            throw new BadRequestException("Page size must not be lower than 0.");
        }
    }

    static void validatePageNumberParameter(String page) {
        int PageInt = Integer.valueOf(page);
        if(PageInt < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }
    }

    static void parameterShouldBeBetweenTwoInteger(String parameterName, String parameterValue, int minValue, int maxValue) {
        float value = Float.valueOf(parameterValue);
        if(value > maxValue || value < minValue) {
            throw new BadRequestException(parameterName + " should be between " + minValue + " and " + maxValue + ".");
        }
    }
}
