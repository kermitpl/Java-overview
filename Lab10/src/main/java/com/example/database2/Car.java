package com.example.database2;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Car")
public class Car {

    @Id
    @Column(name ="VIN")
    private String vin;

    @Column(name = "MANUFACTURER")
    private String Manufacturer;

    @Column(name = "MODEL")
    private String Model;

    @Column(name = "POWER")
    private int Power;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "CAR_OWNER",
            joinColumns = { @JoinColumn(name = "VIN") },
            inverseJoinColumns = { @JoinColumn(name = "PESEL") }
    )
    Set<Owner> owners = new HashSet<>();

    public Car() {}

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getPower() {
        return Power;
    }

    public void setPower(int power) {
        Power = power;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }
}
