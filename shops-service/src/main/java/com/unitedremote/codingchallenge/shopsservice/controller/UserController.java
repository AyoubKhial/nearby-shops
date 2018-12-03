package com.unitedremote.codingchallenge.shopsservice.controller;

import com.unitedremote.codingchallenge.shopsservice.payload.JwtAuthenticationResponse;
import com.unitedremote.codingchallenge.shopsservice.service.UserService;
import com.unitedremote.codingchallenge.shopsservice.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<RestResponse> registerUser(@RequestBody Map<String, String> signUpRequest,
                                                     UriComponentsBuilder uriComponentsBuilder)  {
        RestResponse restResponse = this.userService.register(signUpRequest);
        UriComponents uriComponents = uriComponentsBuilder.path("/users").buildAndExpand();
        return ResponseEntity.created(uriComponents.toUri()).body(restResponse);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@RequestBody Map<String, String> loginRequest) {
        return ResponseEntity.ok(this.userService.login(loginRequest));
    }
}
