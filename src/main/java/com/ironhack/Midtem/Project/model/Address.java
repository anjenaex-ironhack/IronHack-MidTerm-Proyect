package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.enums.City;
import com.ironhack.Midtem.Project.enums.Country;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String address;
    private int postalCode;
    //My idea here should be, in the frontend first choose a Country in a menu, and then get a new list of cities.
    private City City;
    private Country country;

    public Address() {
    }

    public Address(String address, int postalCode, com.ironhack.Midtem.Project.enums.City city, Country country) {
        this.address = address;
        this.postalCode = postalCode;
        City = city;
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

    public com.ironhack.Midtem.Project.enums.City getCity() {
        return City;
    }

    public void setCity(com.ironhack.Midtem.Project.enums.City city) {
        City = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
