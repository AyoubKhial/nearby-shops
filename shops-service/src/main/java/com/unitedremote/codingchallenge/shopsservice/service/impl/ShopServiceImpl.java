package com.unitedremote.codingchallenge.shopsservice.service.impl;

import com.unitedremote.codingchallenge.shopsservice.model.Shop;
import com.unitedremote.codingchallenge.shopsservice.repository.ShopRepository;
import com.unitedremote.codingchallenge.shopsservice.service.ShopService;
import com.unitedremote.codingchallenge.shopsservice.util.PagedResponse;
import com.unitedremote.codingchallenge.shopsservice.util.ValidatingRequestParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * This class is holding the business logic about the <code>Shop</code> resource.
 * @author Ayoub Khial
 * @see Shop
 * @see ShopRepository
 */
@Service
public class ShopServiceImpl implements ShopService {

    private ShopRepository shopRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    /**
     * This method will get all shops sorted by distance from a specific point.
     * @param page the current response page.
     * @param size size of each page in the response.
     * @param longitude the longitude of a position.
     * @param latitude the latitude of a position.
     * @return a paged response that contain all shops.
     */
    @Override
    public PagedResponse<Shop> getAllShopsSortedByDistance(String page, String size, String longitude, String latitude) {

        // check if the page arguments are valid
        ValidatingRequestParameters.parameterShouldBeInteger("page", page);
        ValidatingRequestParameters.parameterShouldBeInteger("size", size);
        ValidatingRequestParameters.parameterShouldBeDouble("longitude", longitude);
        ValidatingRequestParameters.parameterShouldBeDouble("latitude", latitude);
        ValidatingRequestParameters.validatePageNumberParameter(page);
        ValidatingRequestParameters.validatePageSizeParameter(size);
        ValidatingRequestParameters.parameterShouldBeBetweenTwoInteger("longitude", longitude, -180, 180);
        ValidatingRequestParameters.parameterShouldBeBetweenTwoInteger("latitude", latitude, -90, 90);

        // create a pageable from the arguments
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        Page<Shop> shopPage = this.shopRepository.sortedShopsByNearest(pageable, Float.valueOf(longitude), Float.valueOf(latitude));

        if(shopPage.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), shopPage.getNumber(),
                    shopPage.getSize(), shopPage.getTotalElements(), shopPage.getTotalPages(), shopPage.isLast());
        }

        // return the paged response
        return new PagedResponse<>(shopPage.getContent(), shopPage.getNumber(),
                shopPage.getSize(), shopPage.getTotalElements(), shopPage.getTotalPages(), shopPage.isLast());
    }
}