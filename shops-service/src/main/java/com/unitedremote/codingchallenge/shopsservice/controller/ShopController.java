package com.unitedremote.codingchallenge.shopsservice.controller;

import com.unitedremote.codingchallenge.shopsservice.model.Shop;
import com.unitedremote.codingchallenge.shopsservice.security.CurrentUser;
import com.unitedremote.codingchallenge.shopsservice.security.UserPrincipal;
import com.unitedremote.codingchallenge.shopsservice.service.ShopService;
import com.unitedremote.codingchallenge.shopsservice.util.PagedResponse;
import com.unitedremote.codingchallenge.shopsservice.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

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

    @GetMapping("/liked")
    public ResponseEntity<PagedResponse<Shop>> getLikedShopsByUser(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) String page,
                                                                   @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) String size,
                                                                   @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(this.shopService.getLikedShopsByUser(page, size, userPrincipal.getId()));
    }

    @GetMapping("/disliked")
    public ResponseEntity<PagedResponse<Shop>> getDislikedShopsByUser(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) String page,
                                                                      @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) String size,
                                                                      @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(this.shopService.getDislikedShopsByUser(page, size, userPrincipal.getId()));
    }

    @PostMapping("/like")
    public ResponseEntity<RestResponse> addShopToLikedShops(@RequestParam(value = "shop") String shopId,
                                                            @CurrentUser UserPrincipal userPrincipal,
                                                            UriComponentsBuilder uriComponentsBuilder)  {
        RestResponse restResponse = this.shopService.addShopToLikedShops(shopId, userPrincipal.getId());
        UriComponents uriComponents = uriComponentsBuilder.path("/shops").buildAndExpand();
        return ResponseEntity.created(uriComponents.toUri()).body(restResponse);
    }
}
