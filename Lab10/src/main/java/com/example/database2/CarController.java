package com.example.database2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("user")
public class CarController {
    @Autowired
    private ICarService carService;

    @GetMapping("car/{vin}")
    public ResponseEntity<Car> getCarByVin(@PathVariable("vin") String vin) {
        Car car = carService.getCarByVin(vin);
        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }

    @GetMapping("cars")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> list = carService.getAllCars();
        return new ResponseEntity<List<Car>>(list, HttpStatus.OK);
    }

    @GetMapping("carsWithLessPower")
    public ResponseEntity<List<Car>> getCarsProvidedPower(@RequestParam(name="power") int power) {
        List<Car> list = carService.getCarsProvidedPower(power);
        return new ResponseEntity<List<Car>>(list, HttpStatus.OK);
    }

    @GetMapping("carManufacturerModel")
    public ResponseEntity<List<Car>> getCarsProvidedPower(@RequestParam(name="manufacturer") String manufacturer, @RequestParam(name="model") String model) {
        List<Car> list = carService.carFilterManufacturerModel(manufacturer, model);
        return new ResponseEntity<List<Car>>(list, HttpStatus.OK);
    }

    @GetMapping("carModel")
    public ResponseEntity<List<Car>> getCarsProvidedPartiallyModel( @RequestParam(name="model") String model) {
        List<Car> list = carService.carFilterModel(model);
        return new ResponseEntity<List<Car>>(list, HttpStatus.OK);
    }

    @PostMapping("car")
    public ResponseEntity<Void> addCar(@RequestBody Car car, UriComponentsBuilder builder) {
        boolean flag = carService.addCar(car);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/car/{vin}").buildAndExpand(car.getVin()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("car")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        carService.updateCar(car);
        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }

    @PutMapping("carAddOwner")
    public ResponseEntity<Void> addOwner(@RequestParam("vin") String vin, @RequestParam("PESEL") float PESEL) {
        carService.addOwner(vin, PESEL);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("carDeleteOwner")
    public ResponseEntity<Void> deleteOwner(@RequestParam("vin") String vin, @RequestParam("PESEL") float PESEL) {
        carService.deleteOwner(vin, PESEL);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("car/{vin}")
    public ResponseEntity<Void> deleteCar(@PathVariable("vin") String vin) {
        carService.deleteCar(vin);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    void handleException(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

}
