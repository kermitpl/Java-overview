package com.example.adrian.myweatherapp;

import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    private double speed;

    public Wind() {
    }

    public double getSpeed() {
        return speed;
    }

    public Wind(double speed) {
        this.speed = speed;
    }
}
