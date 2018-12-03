package com.unitedremote.codingchallenge.shopsservice.util;

import com.unitedremote.codingchallenge.shopsservice.exception.BadRequestException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidatingRequestBodyTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        this.expectedException.expect(BadRequestException.class);
    }

    @Test
    public void keyValueShouldNotBeNull_NullValue_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("email must be set.");
        ValidatingRequestBody.keyValueShouldNotBeNull(null, "email");
    }

    @Test
    public void keyValueLengthShouldBeBetween_Size_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("name should contain 10 to 30 character.");
        ValidatingRequestBody.keyValueLengthShouldBeBetween("random", 10, 30, "name");
    }

    @Test
    public void keyValueShouldBeEmail_KeyValue_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("Please enter a valid email.");
        ValidatingRequestBody.keyValueShouldBeEmail("random");
    }
}
