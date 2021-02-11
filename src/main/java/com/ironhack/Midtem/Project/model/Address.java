package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.enums.City;
import com.ironhack.Midtem.Project.enums.Country;

import javax.persistence.*;
import java.util.List;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String address;
    private int postalCode;
    private String city;
    private String country;
    //My idea here should be, in the frontend first choose a Country in a menu, and then get a new list of cities.
//    @Enumerated(EnumType.STRING)
//    private City city;
//    @Enumerated(EnumType.STRING)
//    private Country country;

//    @OneToOne(mappedBy = "address")
//    private AccountHolder accountHolder;


    public Address() {
    }

    public Address(String address, int postalCode, String city, String country) {
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
