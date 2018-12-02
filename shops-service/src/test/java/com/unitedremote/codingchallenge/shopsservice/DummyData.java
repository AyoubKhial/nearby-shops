package com.unitedremote.codingchallenge.shopsservice;

import com.unitedremote.codingchallenge.shopsservice.model.User;
import com.unitedremote.codingchallenge.shopsservice.security.UserPrincipal;

/**
 * This class contain some dummy data which will be used in the test
 */
public final class DummyData {

    public static User dummyUser() {
        return new User("Ayouub.Khial@gmail.com", "123456789");
    }

    public static UserPrincipal dummyUserPrincipal() {
        return new UserPrincipal(dummyUser().getId(), dummyUser().getEmail(),
                dummyUser().getPassword());
    }
}
