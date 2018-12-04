package com.unitedremote.codingchallenge.shopsservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * This class will hold the basic structure of a document in the <b>shops</b> collection.
 * @author Ayoub Khial
 */
@Document(collection = "shops")
public class Shop {
    @Id
    private String id;
    private String picture;
    private String name;
    private String email;
    private String city;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Location location;

    public Shop() {}

    public Shop(String picture, String name, String email, String city, Location location) {
        this.picture = picture;
        this.name = name;
        this.email = email;
        this.city = city;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(id, shop.id) &&
                Objects.equals(picture, shop.picture) &&
                Objects.equals(name, shop.name) &&
                Objects.equals(email, shop.email) &&
                Objects.equals(city, shop.city) &&
                Objects.equals(location, shop.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, picture, name, email, city, location);
    }
}
