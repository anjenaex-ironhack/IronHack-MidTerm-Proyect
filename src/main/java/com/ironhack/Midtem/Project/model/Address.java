package com.ironhack.Midtem.Project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

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

//    @JsonIgnore
//    @OneToOne(mappedBy = "address")
//    private AccountHolder accountHolder;


    public Address() {
    }

    /**
     * Class constructor for an address.
     **/
    public Address(String address, int postalCode, String city, String country) {
        setAddress(address);
        setPostalCode(postalCode);
        setCity(city);
        setCountry(country);
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
