package com.unitedremote.codingchallenge.shopsservice.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /***
     * This method is called whenever an exception is thrown due to an unauthenticated user trying to access a resource
     * that requires authentication.
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {
        if(("/api/auth/signin").equals(httpServletRequest.getRequestURI())) {
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.getOutputStream().println("{ \"status\": \"Bad Request\",");
            httpServletResponse.getOutputStream().println("\"error\": 404,");
            httpServletResponse.getOutputStream().println("\"message\": \"The email or password are incorrect.\"}");
        }
        else {
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getOutputStream().println("{ \"status\": \"Unauthorized\",");
            httpServletResponse.getOutputStream().println("\"error\": 401,");
            httpServletResponse.getOutputStream().println("\"message\": \"Sorry, You're not authorized to access this resource.\"}");
        }
    }
}