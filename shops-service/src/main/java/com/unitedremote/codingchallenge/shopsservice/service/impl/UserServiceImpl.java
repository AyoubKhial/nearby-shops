package com.unitedremote.codingchallenge.shopsservice.service.impl;

import com.unitedremote.codingchallenge.shopsservice.exception.BadRequestException;
import com.unitedremote.codingchallenge.shopsservice.model.User;
import com.unitedremote.codingchallenge.shopsservice.repository.UserRepository;
import com.unitedremote.codingchallenge.shopsservice.service.UserService;
import com.unitedremote.codingchallenge.shopsservice.util.HTTPCode;
import com.unitedremote.codingchallenge.shopsservice.util.RestResponse;
import com.unitedremote.codingchallenge.shopsservice.util.ValidatingRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * This class is holding the business logic about the <code>User</code> resource.
 * @author Ayoub Khial
 * @see User
 * @see UserRepository
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * This method will register a new user into our database.
     * @param signUpRequest the request body which contain an email and a password.
     * @return a rest response if the user successfully registered, otherwise will throw a badRequestException.
     */
    @Override
    public RestResponse register(Map<String, String> signUpRequest) {
        // getting the values from the map
        String email = signUpRequest.get("email");
        String password = signUpRequest.get("password");
        // required fields
        ValidatingRequestBody.keyValueShouldNotBeNull(email, "email");
        ValidatingRequestBody.keyValueShouldNotBeNull(password, "password");
        // email validation
        ValidatingRequestBody.keyValueShouldBeEmail(email);
        ValidatingRequestBody.keyValueLengthShouldBeBetween(email, 3, 255, "email");
        // password validation
        ValidatingRequestBody.keyValueLengthShouldBeBetween(password, 8, 30, "password");
        // phone validation
        // check if email already exists
        if(this.userRepository.existsByEmail(email.toLowerCase())) {
            throw new BadRequestException("Email Address already in use.");
        }
        // create a user object to save in the database
        User user = new User(email.toLowerCase(), password);
        // hash the user password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        // save the user un the database
        this.userRepository.save(user);
        // return a rest response with CREATED
        return new RestResponse(HTTPCode.CREATED.getValue(), HTTPCode.CREATED.getKey(),
                "Congratulations! You've successfully registered.",
                "User registered successfully.");
    }
}
