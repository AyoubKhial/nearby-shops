package com.unitedremote.codingchallenge.shopsservice.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represent the embedded document <code>location</code>.
 */
public class Location {

    private String type;
    private double[] coordinates;

    public Location() {}

    public Location(String type, double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(type, location.type) &&
                Arrays.equals(coordinates, location.coordinates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(type);
        result = 31 * result + Arrays.hashCode(coordinates);
        return result;
    }
}
