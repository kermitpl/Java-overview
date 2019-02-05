package com.example.adrian.myweatherapp;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("pressure")
    private double pressure;
    @SerializedName("temp")
    private double temp;

    public Weather() {
    }

    public double getPressure() {
        return pressure;
    }

    public double getTemp() {
        return temp;
    }

    public Weather(double pressure, double temp) {
        this.pressure = pressure;
        this.temp = temp;
    }
}
