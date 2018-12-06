package com.unitedremote.codingchallenge.shopsservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unitedremote.codingchallenge.shopsservice.DummyData;
import com.unitedremote.codingchallenge.shopsservice.exception.GlobalRestExceptionHandler;
import com.unitedremote.codingchallenge.shopsservice.model.Shop;
import com.unitedremote.codingchallenge.shopsservice.service.ShopService;
import com.unitedremote.codingchallenge.shopsservice.util.PagedResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class ShopControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ShopService shopService;

    @InjectMocks
    private ShopController shopController;

    private PagedResponse<Shop> pagedShopResponse;

    // This object will be initialized by the initFields method below.
    private JacksonTester<PagedResponse<Shop>> jsonShop;

    @Before
    public void setUp() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        this.mockMvc = MockMvcBuilders.standaloneSetup(shopController)
                .setControllerAdvice(new GlobalRestExceptionHandler())
                .build();
        this.pagedShopResponse = DummyData.dummyPagedShopResponse();
    }

    @Test
    public void getAllShopsSortedByDistance_CoordinatesGiven_ShouldReturnPagedShopResponse() throws Exception {

        // given
        given(this.shopService.getAllShopsSortedByDistance(anyString(), anyString(), anyString(), anyString()))
                .willReturn(this.pagedShopResponse);

        // when
        MockHttpServletResponse response = this.mockMvc.perform(get("/shops?longitude=-6.75175&latitude=33.96853")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        PagedResponse<Shop> expectedPagedShopResponse = new PagedResponse<>(Collections.singletonList(DummyData.dummyShop()), 0, 0, 1, 1, true);
        assertThat(response.getContentAsString()).isEqualTo(this.jsonShop.write(expectedPagedShopResponse).getJson());
    }

    @After
    public void tearDown() {
        this.pagedShopResponse = null;
    }
}
