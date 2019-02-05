package com.example.database2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements ICarService {

    @Autowired
    private ICarDAO carDAO;

    @Override
    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    @Override
    public Car getCarByVin(String vin) {
        Car obj = carDAO.getCarByVin(vin);
        return obj;
    }

    @Override
    public synchronized boolean addCar(Car car) {
        if (carDAO.carExists(car.getVin())) {
            return false;
        } else {
            carDAO.addCar(car);
            return true;
        }
    }

    @Override
    public void updateCar(Car car) {
        carDAO.updateCar(car);
    }

    @Override
    public void deleteCar(String car) {
        carDAO.deleteCar(car);
    }

    @Override
    public void addOwner(String vin, float PESEL) {
        carDAO.addOwner(vin, PESEL);
    }

    @Override
    public void deleteOwner(String vin, float PESEL) {
        carDAO.deleteOwner(vin, PESEL);
    }

    @Override
    public List<Car> getCarsProvidedPower(int power) {
        return carDAO.getCarsProvidedPower(power);
    }

    @Override
    public List<Car> carFilterManufacturerModel(String manufacturer, String model)
    {
        return carDAO.carFilterManufacturerModel(manufacturer, model);
    }

    @Override
    public List<Car> carFilterModel(String model) {
        return carDAO.carFilterModel(model);
    }
}
