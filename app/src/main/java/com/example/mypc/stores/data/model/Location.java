
package com.example.mypc.stores.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable{

    @SerializedName("locationId")
    @Expose
    private long locationId;
    @SerializedName("locationLat")
    @Expose
    private double locationLat;
    @SerializedName("locationLng")
    @Expose
    private double locationLng;


    /**
     * No args constructor for use in serialization
     */
    public Location() {
    }

    public Location(long locationId, double locationLat, double locationLng) {
        this.locationId = locationId;
        this.locationLat = locationLat;
        this.locationLng = locationLng;

    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(double locationLat) {
        this.locationLat = locationLat;
    }

    public double getLocationLng() {
        return locationLng;
    }

    public void setLocationLng(double locationLng) {
        this.locationLng = locationLng;
    }

}