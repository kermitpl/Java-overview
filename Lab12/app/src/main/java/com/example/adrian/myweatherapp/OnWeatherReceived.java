package com.example.adrian.myweatherapp;


public interface OnWeatherReceived {
    void onDataHandle(Weather weather, Wind wind, Coordinates coordinates, Icon icon);
}
