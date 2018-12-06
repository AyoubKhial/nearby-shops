package com.unitedremote.codingchallenge.shopsservice.repository;

import com.unitedremote.codingchallenge.shopsservice.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomShopRepository {

    Page<Shop> getLikedShopsByUser(Pageable pageable, String userId);

    Page<Shop> getDislikedShopsByUser(Pageable pageable, String userId);

    void addShopToLikedShops(String shopId, String userId);
}
