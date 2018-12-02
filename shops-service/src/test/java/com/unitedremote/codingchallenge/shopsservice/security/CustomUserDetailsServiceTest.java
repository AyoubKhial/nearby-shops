package com.unitedremote.codingchallenge.shopsservice.security;

import com.unitedremote.codingchallenge.shopsservice.DummyData;
import com.unitedremote.codingchallenge.shopsservice.model.User;
import com.unitedremote.codingchallenge.shopsservice.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomUserDetailsServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;
    private User user;
    private UserPrincipal userPrincipal;

    @Before
    public void setUp() {
        this.user = DummyData.dummyUser();
        this.userPrincipal = DummyData.dummyUserPrincipal();
    }

    @Test
    public void loadUserByUsername_EmailNotFound_ShouldThrowUsernameNotFoundException() {
        this.expectedException.expect(UsernameNotFoundException.class);
        this.expectedException.expectMessage("User not found with {email: 'Ayouub.Khial@gmail.com'}.");

        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        this.customUserDetailsService.loadUserByUsername("Ayouub.Khial@gmail.com");
    }

    @Test
    public void loadUserByUsername_Email_ShouldCreateUserPrincipalInstance() {
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.of(this.user));
        // actual result
        UserDetails actualUserPrincipal = this.customUserDetailsService.loadUserByUsername(anyString());

        assertThat("The actual response is different than the expected.", this.userPrincipal, is(equalTo(actualUserPrincipal)));
    }

    @Test
    public void loadUserById_IdNotFound_ShouldThrowUsernameNotFoundException() {
        this.expectedException.expect(UsernameNotFoundException.class);
        this.expectedException.expectMessage("User not found with {id: '1'}.");

        when(this.userRepository.findById(anyString())).thenReturn(Optional.empty());
        this.customUserDetailsService.loadUserById("1");
    }

    @Test
    public void loadUserByUsername_Id_ShouldCreateUserPrincipalInstance() {
        when(this.userRepository.findById(anyString())).thenReturn(Optional.of(this.user));
        // actual result
        UserDetails actualUserPrincipal = this.customUserDetailsService.loadUserById(anyString());

        assertThat("The actual response is different than the expected.", this.userPrincipal, is(equalTo(actualUserPrincipal)));
    }

}