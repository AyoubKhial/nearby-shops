package com.unitedremote.codingchallenge.shopsservice.service;

import com.unitedremote.codingchallenge.shopsservice.payload.JwtAuthenticationResponse;
import com.unitedremote.codingchallenge.shopsservice.util.RestResponse;

import java.util.Map;

public interface UserService {

    RestResponse register(Map<String, String> signUpRequest);

    JwtAuthenticationResponse login(Map<String, String> loginRequest);
}
