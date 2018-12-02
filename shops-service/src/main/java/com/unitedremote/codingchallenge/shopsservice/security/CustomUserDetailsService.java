package com.unitedremote.codingchallenge.shopsservice.security;

import com.unitedremote.codingchallenge.shopsservice.model.User;
import com.unitedremote.codingchallenge.shopsservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class loads a user's data given its email.
 * @author Ayoub Khial
 * @see UserDetailsService
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        // Let people login with email
        User user = this.userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with {email: '" + email + "'}.")
        );
        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(String id) {
        User user = this.userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with {id: '" + id + "'}.")
        );

        return UserPrincipal.create(user);
    }
}