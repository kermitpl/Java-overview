package com.example.database2;

import java.util.List;

public interface ICarService {
    List<Car> getAllCars();

    Car getCarByVin(String vin);

    boolean addCar(Car car);

    void updateCar(Car car);

    void deleteCar(String car);

    void addOwner(String vin, float PESEL);

    void deleteOwner(String vin, float PESEL);

    List<Car> getCarsProvidedPower(int power);

    List<Car> carFilterManufacturerModel(String manufacturer, String model);

    List<Car> carFilterModel(String model);
}
