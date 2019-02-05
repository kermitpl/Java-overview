package com.example.adrian.myweatherapp;

import com.google.gson.annotations.SerializedName;

public class Coordinates {
    @SerializedName("lon")
    private double lon;
    @SerializedName("lat")
    private double lat;

    public Coordinates() {
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public Coordinates(double lat, double lon) {
        this.lon = lon;
        this.lat = lat;
    }
}
