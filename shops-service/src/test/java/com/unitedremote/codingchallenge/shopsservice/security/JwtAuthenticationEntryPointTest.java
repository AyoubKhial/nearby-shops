package com.unitedremote.codingchallenge.shopsservice.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { JwtAuthenticationEntryPoint.class })
public class JwtAuthenticationEntryPointTest {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Test
    public void commence_ShouldReturnBadRequestResponse() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException ex = new AuthenticationCredentialsNotFoundException("");
        request.setRequestURI("/api/v1/users/signin");
        this.jwtAuthenticationEntryPoint.commence(request, response, ex);

        assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());
    }

    @Test
    public void commence_ShouldReturnUnauthorizedResponse() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException ex = new AuthenticationCredentialsNotFoundException("");

        this.jwtAuthenticationEntryPoint.commence(request, response, ex);

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
    }
}
