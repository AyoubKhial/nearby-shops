package com.unitedremote.codingchallenge.shopsservice.controller;

import com.unitedremote.codingchallenge.shopsservice.model.Shop;
import com.unitedremote.codingchallenge.shopsservice.service.ShopService;
import com.unitedremote.codingchallenge.shopsservice.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.unitedremote.codingchallenge.shopsservice.util.ApplicationConstants.DEFAULT_PAGE_NUMBER;
import static com.unitedremote.codingchallenge.shopsservice.util.ApplicationConstants.DEFAULT_PAGE_SIZE;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/shops")
public class ShopController {

    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Shop>> getAllShopsSortedByDistance(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) String page,
                                                       @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) String size,
                                                       @RequestParam(value = "longitude", defaultValue = "createdAt") String longitude,
                                                       @RequestParam(value = "latitude", defaultValue = "desc") String latitude) {
        return ResponseEntity.ok(this.shopService.getAllShopsSortedByDistance(page, size, longitude, latitude));
    }
}
