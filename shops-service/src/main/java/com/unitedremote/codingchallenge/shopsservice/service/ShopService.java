package com.unitedremote.codingchallenge.shopsservice.service;

import com.unitedremote.codingchallenge.shopsservice.model.Shop;
import com.unitedremote.codingchallenge.shopsservice.util.PagedResponse;

public interface ShopService {

    PagedResponse<Shop> getAllShopsSortedByDistance(String page, String size, String longitude, String latitude);
}
