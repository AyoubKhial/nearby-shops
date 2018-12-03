package com.unitedremote.codingchallenge.shopsservice;

import com.unitedremote.codingchallenge.shopsservice.model.User;
import com.unitedremote.codingchallenge.shopsservice.security.UserPrincipal;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contain some dummy data which will be used in the test
 */
public final class DummyData {

    public static User dummyUser() {
        return new User("Ayouub.Khial@gmail.com", "123456789");
    }

    public static UserPrincipal dummyUserPrincipal() {
        return new UserPrincipal(dummyUser().getId(), dummyUser().getEmail(), dummyUser().getPassword());
    }

    public static Map<String, String> dummyLoginOrSignUpRequest(){
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", dummyUser().getEmail());
        loginRequest.put("password", dummyUser().getPassword());
        return loginRequest;
    }
}
