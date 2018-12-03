package com.unitedremote.codingchallenge.shopsservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unitedremote.codingchallenge.shopsservice.DummyData;
import com.unitedremote.codingchallenge.shopsservice.payload.JwtAuthenticationResponse;
import com.unitedremote.codingchallenge.shopsservice.service.UserService;
import com.unitedremote.codingchallenge.shopsservice.util.HTTPCode;
import com.unitedremote.codingchallenge.shopsservice.util.RestResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private Map<String, String> LoginOrSignUpRequest;
    private JacksonTester<Map<String, String>> LoginOrSignUpRequestJson = null;

    @Before
    public void setUp() {
        this.LoginOrSignUpRequest = DummyData.dummyLoginOrSignUpRequest();
        // initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void register_SignupRequest_ShouldReturnRestResponseWithCreated() throws Exception {
        RestResponse restResponse = new RestResponse(HTTPCode.CREATED.getValue(), HTTPCode.CREATED.getKey(),
                "User successfully registered.");

        given(this.userService.register(this.LoginOrSignUpRequest)).willReturn(restResponse);

        MockHttpServletResponse response =  this.mockMvc.perform(
                post("/users/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(LoginOrSignUpRequestJson.write(this.LoginOrSignUpRequest).getJson()))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void login_LoginRequest_ShouldReturnJwtAuthenticationResponse() throws Exception {
        given(this.userService.login(this.LoginOrSignUpRequest)).willReturn(new JwtAuthenticationResponse("token"));

        MockHttpServletResponse response =  this.mockMvc.perform(
                post("/users/signin")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(LoginOrSignUpRequestJson.write(this.LoginOrSignUpRequest).getJson()))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
