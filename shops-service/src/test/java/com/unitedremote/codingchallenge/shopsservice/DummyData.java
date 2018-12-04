package com.unitedremote.codingchallenge.shopsservice;

import com.unitedremote.codingchallenge.shopsservice.model.Location;
import com.unitedremote.codingchallenge.shopsservice.model.Shop;
import com.unitedremote.codingchallenge.shopsservice.model.User;
import com.unitedremote.codingchallenge.shopsservice.security.UserPrincipal;
import com.unitedremote.codingchallenge.shopsservice.util.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;

/**
 * This class contain some dummy data which will be used in the test
 */
public final class DummyData {

    public static User dummyUser() {
        return new User("Ayouub.Khial@gmail.com", "123456789");
    }

    public static UserPrincipal dummyUserPrincipal() {
        return new UserPrincipal(dummyUser().getId(), dummyUser().getEmail(), dummyUser().getPassword());
    }

    public static Map<String, String> dummyLoginOrSignUpRequest(){
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", dummyUser().getEmail());
        loginRequest.put("password", dummyUser().getPassword());
        return loginRequest;
    }

    public static Shop dummyShop () {
        double[] point = {-6.75175, 33.96853};
        Location location = new Location("Point", point);
        return new Shop("picture.jpg", "Stoon", "stoon@gmail.com", "Oujda", location);
    }

    public static Page<Shop> dummyShopPage() {
        List<Shop> shopList = new ArrayList<>(Collections.singletonList(dummyShop()));
        return new PageImpl<>(shopList);
    }

    public static PagedResponse<Shop> dummyPagedShopResponse() {
        List<Shop> shopResponseList = dummyShopPage().getContent();
        return new PagedResponse<>(shopResponseList, dummyShopPage().getNumber(), dummyShopPage().getSize(),
                dummyShopPage().getTotalElements(), dummyShopPage().getTotalPages(), dummyShopPage().isLast());
    }
}
