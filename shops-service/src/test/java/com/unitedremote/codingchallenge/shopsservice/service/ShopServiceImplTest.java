package com.unitedremote.codingchallenge.shopsservice.service;

import com.unitedremote.codingchallenge.shopsservice.DummyData;
import com.unitedremote.codingchallenge.shopsservice.model.Shop;
import com.unitedremote.codingchallenge.shopsservice.repository.ShopRepository;
import com.unitedremote.codingchallenge.shopsservice.service.impl.ShopServiceImpl;
import com.unitedremote.codingchallenge.shopsservice.util.PagedResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.*;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ShopServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule().strictness(Strictness.LENIENT);
    @Mock
    private ShopRepository shopRepository;
    @InjectMocks
    private ShopServiceImpl shopService;
    private Page<Shop> shopPage;
    private PagedResponse<Shop> pagedShopResponse;

    @Before
    public void setUp() {
        this.shopPage = DummyData.dummyShopPage();
        this.pagedShopResponse = DummyData.dummyPagedShopResponse();
    }

    @Test
    public void getAllShopsSortedByDistance_CoordinatesGiven_ShouldReturnPagedShopsResponse() {
        when(this.shopRepository.sortedShopsByNearest(any(PageRequest.class), anyDouble(), anyDouble()))
                .thenReturn(this.shopPage);

        PagedResponse<Shop> actualPagedShopResponse = this.shopService.getAllShopsSortedByDistance("0", "10", "-6.75175", "33.96853");

        assertThat("The actual response is different than the expected.", actualPagedShopResponse, is(equalTo(this.pagedShopResponse)));
    }

    @Test
    public void getAllShopsSortedByDistance_CoordinatesGiven_ShouldReturnEmptyPagedShopsResponse() {
        when(this.shopRepository.sortedShopsByNearest(any(PageRequest.class), anyDouble(), anyDouble()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        PagedResponse<Shop> actualShopResponsePage = this.shopService.getAllShopsSortedByDistance("0", "10", "-6.75175", "33.96853");

        PagedResponse<Shop> expectedShopResponsePage = new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 1, true);

        assertThat("The actual response is different than the expected.", actualShopResponsePage, is(equalTo(expectedShopResponsePage)));
    }

    @After
    public void tearDown() {
        this.shopPage = null;
        this.pagedShopResponse = null;
    }
}
