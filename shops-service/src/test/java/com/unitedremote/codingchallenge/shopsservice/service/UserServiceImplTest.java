package com.unitedremote.codingchallenge.shopsservice.service;


import com.unitedremote.codingchallenge.shopsservice.DummyData;
import com.unitedremote.codingchallenge.shopsservice.exception.BadRequestException;
import com.unitedremote.codingchallenge.shopsservice.model.User;
import com.unitedremote.codingchallenge.shopsservice.repository.UserRepository;
import com.unitedremote.codingchallenge.shopsservice.service.impl.UserServiceImpl;
import com.unitedremote.codingchallenge.shopsservice.util.HTTPCode;
import com.unitedremote.codingchallenge.shopsservice.util.RestResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule().strictness(Strictness.LENIENT);
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;
    private Map<String, String> loginOrSignUpRequest;
    private User user;

    @Before
    public void setUp() {
        // initialize a loginOrSignUpRequest object with some dummy data
        this.loginOrSignUpRequest = DummyData.dummyLoginOrSignUpRequest();
        // initialize a user object with some dummy data
        this.user = DummyData.dummyUser();
        this.user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    }

    /* --- Testing register method -- */
    @Test
    public void register_SignupRequestWithAlreadyExistEmail_ShouldThrowBadRequestException() {
        // the exception with the message expected
        this.expectedException.expect(BadRequestException.class);
        this.expectedException.expectMessage("Email Address already in use.");
        // mock the user repository existsByEmail method with random string to return true
        when(this.userRepository.existsByEmail(anyString())).thenReturn(true);
        // call to the tested method
        this.userService.register(this.loginOrSignUpRequest);
    }

    @Test
    public void register_ValidSignupRequest_ShouldReturnRestResponseWithCreated() {
        // mock the user repository existsByEmail method with random string to return false
        when(this.userRepository.existsByEmail(anyString())).thenReturn(false);
        // mock the user repository save method to return the saved user
        when(this.userRepository.save(any(User.class))).thenReturn(this.user);
        // call to the tested method
        RestResponse actualRestResponse = this.userService.register(this.loginOrSignUpRequest);
        // the expected result
        RestResponse expectedRestResponse = new RestResponse(HTTPCode.CREATED.getValue(), HTTPCode.CREATED.getKey(),
                "Congratulations! You've successfully registered.",
                "User registered successfully.");
        // check equality between the current result and the expected one
        assertThat(actualRestResponse, is(equalTo(expectedRestResponse)));
    }
}
