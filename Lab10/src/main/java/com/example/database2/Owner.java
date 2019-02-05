package com.example.database2;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Owner")
public class Owner {

    @Id
    @Column(name ="PESEL")
    private float PESEL;

    @NotNull(message="Name cannot be null")
    @Column(name ="NAME")
    private String name;

    @NotNull(message="Surname cannot be null")
    @Column(name ="SURNAME")
    private String surname;

    @Column(name ="AGE")
    private int age;

    @Email(message = "Email did not meet expectations.")
    @Column(name ="EMAIL")
    private String email;

    @ManyToMany(mappedBy = "owners")
    private Set<Car> cars = new HashSet<>();

    public Owner() {}

    public float getPESEL() {
        return PESEL;
    }

    public void setPESEL(float PESEL) {
        this.PESEL = PESEL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
