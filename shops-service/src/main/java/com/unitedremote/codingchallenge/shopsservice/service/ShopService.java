package com.unitedremote.codingchallenge.shopsservice.service;

import com.unitedremote.codingchallenge.shopsservice.model.Shop;
import com.unitedremote.codingchallenge.shopsservice.util.PagedResponse;

public interface ShopService {

    PagedResponse<Shop> getAllShopsSortedByDistance(String page, String size, String longitude, String latitude);

    PagedResponse<Shop> getLikedShopsByUser(String page, String size, String userId);

    PagedResponse<Shop> getDislikedShopsByUser(String page, String size, String userId);
}
