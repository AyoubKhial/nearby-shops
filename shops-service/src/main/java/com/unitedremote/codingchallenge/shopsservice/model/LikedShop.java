package com.unitedremote.codingchallenge.shopsservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class will hold the basic structure of a document in the <b>LikedShops</b> collection.
 * @author Ayoub Khial
 */
@Document(collection = "likedShops")
public class LikedShop {

    @Id
    private String id;
    @DBRef(lazy = true, db = "users")
    private String user;
    @DBRef(lazy = true, db = "shops")
    private String shop;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }
}
