package com.example.database2;

import com.example.database2.Car;

import java.util.List;

public interface ICarDAO {
    List<Car> getAllCars();
    Car getCarByVin(String vin);
    void addCar(Car car);
    void updateCar(Car car);
    void deleteCar(String vin);
    boolean carExists(String vin);
    void addOwner(String vin, float PESEL);
    void deleteOwner(String vin, float PESEL);
    List<Car> getCarsProvidedPower(int power);
    List<Car> carFilterManufacturerModel(String manufacturer, String model);
    List<Car> carFilterModel(String model);
}
