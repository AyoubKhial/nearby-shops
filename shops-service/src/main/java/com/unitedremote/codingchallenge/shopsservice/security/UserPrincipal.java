package com.unitedremote.codingchallenge.shopsservice.security;

import com.unitedremote.codingchallenge.shopsservice.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * This is the class whose instances will be returned from our custom {@link CustomUserDetailsService}.
 * Spring Security will use the information stored in the <code>UserPrincipal</code> object to perform authentication
 * and authorization.
 * @author Ayoub Khial
 * @see UserDetails
 */
public class UserPrincipal implements UserDetails {

    private String id;
    private String username;
    private String password;
    private static final Set<GrantedAuthority> authorities = new HashSet<>();

    public UserPrincipal(String id, String email, String password) {
        this.id = id;
        this.username = email;
        this.password = password;
        authorities.add(new SimpleGrantedAuthority("USER"));
    }

    public static UserPrincipal create(User user) {
        authorities.add(new SimpleGrantedAuthority("USER"));

        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public String getId() {
        return id;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}