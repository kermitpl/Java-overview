package com.example.adrian.myweatherapp;

import com.google.gson.annotations.SerializedName;

public class Icon {
    @SerializedName("icon")
    private String icon;

    public Icon() {
    }

    public String getIcon() {
        return icon;
    }

    public Icon(String icon) {
        this.icon = icon;
    }
}
