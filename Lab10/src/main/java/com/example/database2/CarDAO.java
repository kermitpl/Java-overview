package com.example.database2;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class CarDAO implements ICarDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Car getCarByVin(String vin) {
        return entityManager.find(Car.class, vin);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Car> getAllCars() {
        String hql = "FROM Car as car ORDER BY car.vin";
        return (List<Car>) entityManager.createQuery(hql).getResultList();
    }
    @Override
    public void addCar(Car car) {
        entityManager.persist(car);
    }
    @Override
    public void updateCar(Car car) {
        Car carl = getCarByVin(car.getVin());
        carl.setManufacturer(car.getManufacturer());
        carl.setModel(car.getModel());
        carl.setPower(car.getPower());
        entityManager.flush();
    }
    @Override
    public void deleteCar(String vin) {
        entityManager.remove(getCarByVin(vin));
    }
    @Override
    public boolean carExists(String vin) {
        String hql = "FROM Car as car WHERE car.vin = '"+vin+"'";
        int count = entityManager.createQuery(hql).getResultList().size();
        return count > 0 ? true : false;
    }
    @Override
    public void addOwner(String vin, float PESEL){
        Car car = getCarByVin(vin);
        Owner owner = entityManager.find(Owner.class, PESEL);
        car.owners.add(owner);
    }
    @Override
    public void deleteOwner(String vin, float PESEL){
        Car car = getCarByVin(vin);
        Owner owner = entityManager.find(Owner.class, PESEL);
        car.owners.remove(owner);
    }
    @Override
    public List<Car> getCarsProvidedPower(int power) {
        String hql = "FROM Car as car WHERE car.Power < "+power+" ORDER BY car.vin";
        return (List<Car>) entityManager.createQuery(hql).getResultList();
    }
    @Override
    public List<Car> carFilterManufacturerModel(String manufacturer, String model) {
        String hql = "FROM Car as car WHERE car.Manufacturer = '"+manufacturer+"' AND car.Model = '"+model+"'";
        return (List<Car>) entityManager.createQuery(hql).getResultList();
    }
    @Override
    public List<Car> carFilterModel(String model) {
        String hql = "FROM Car as car WHERE car.Model LIKE '%"+model+"%'";
        return (List<Car>) entityManager.createQuery(hql).getResultList();
    }
}
