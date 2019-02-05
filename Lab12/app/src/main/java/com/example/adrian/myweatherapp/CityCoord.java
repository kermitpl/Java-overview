package com.example.adrian.myweatherapp;

public enum CityCoord {
    WROCLAW("Wroclaw", new Coordinates(51.110, 17.030)),
    WARSZAWA("Warszawa", new Coordinates(52.259, 21.020)),
    LESZNOWOLA("Lesznowola", new Coordinates(51.905871, 20.903541)),
    SOSNOWIEC("Sosnowiec", new Coordinates(50.285273, 19.103944)),
    SOPOT("Sopot", new Coordinates(54.445466, 18.571524));

    private String city;
    private Coordinates coordinates;

    CityCoord(String city, Coordinates coordinates) {
        this.city = city;
        this.coordinates = coordinates;
    }

    public String getCity() {
        return city;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
