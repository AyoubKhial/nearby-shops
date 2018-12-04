package com.unitedremote.codingchallenge.shopsservice.util;

import com.unitedremote.codingchallenge.shopsservice.exception.BadRequestException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidatingRequestParametersTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        this.expectedException.expect(BadRequestException.class);
    }

    @Test
    public void parameterShouldBeInteger_NonIntegerParameter_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("page parameter accept integers only, '2.5' is not an integer.");
        ValidatingRequestParameters.parameterShouldBeInteger("page", "2.5");
    }

    @Test
    public void parameterShouldBeDouble_NonDoubleParameter_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("latitude parameter accept double only, 'a' is not a double.");
        ValidatingRequestParameters.parameterShouldBeDouble("latitude", "a");
    }

    @Test
    public void validatePageSizeParameter_ParameterGreaterThanMax_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("Page size must not be greater than 50.");
        ValidatingRequestParameters.validatePageSizeParameter("80");
    }

    @Test
    public void validatePageSizeParameter_ParameterLowerThanZero_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("Page size must not be lower than 0.");
        ValidatingRequestParameters.validatePageSizeParameter("-1");
    }

    @Test
    public void validatePageNumberParameter_ParameterLowerThanZero_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("Page number cannot be less than zero.");
        ValidatingRequestParameters.validatePageNumberParameter("-1");
    }

    @Test
    public void parameterShouldBeBetweenTwoInteger_ParameterLowerThanMin_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("latitude should be between 1 and 10.");
        ValidatingRequestParameters.parameterShouldBeBetweenTwoInteger("latitude", "0", 1, 10);
    }
}
