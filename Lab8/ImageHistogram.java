package com.example.springbootimage;

public class ImageHistogram {
    private double[] red;
    private double[] green;
    private double[] blue;

    public ImageHistogram(double[] red, double[] green, double[] blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public double[] getRed() {
        return red;
    }

    public double[] getGreen() {
        return green;
    }

    public double[] getBlue() {
        return blue;
    }

    public void setRed(double[] red) {
        this.red = red;
    }

    public void setGreen(double[] green) {
        this.green = green;
    }

    public void setBlue(double[] blue) {
        this.blue = blue;
    }
}
