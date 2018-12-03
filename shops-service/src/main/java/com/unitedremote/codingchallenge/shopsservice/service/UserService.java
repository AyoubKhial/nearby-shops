package com.unitedremote.codingchallenge.shopsservice.service;

import com.unitedremote.codingchallenge.shopsservice.util.RestResponse;

import java.util.Map;

public interface UserService {

    RestResponse register(Map<String, String> signUpRequest);
}
