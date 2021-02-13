package com.ironhack.Midtem.Project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User {

    private String dni;
    private LocalDate birth;

    @OneToOne
    @JoinColumn(name = "address")
    private Address address;

    @OneToOne
    @JoinColumn(name = "mailing_address")
    private Address mailingAddress;

    //Ignores because of postman troubles
    @JsonIgnore
    @OneToMany(mappedBy = "primaryOwner")
    private Account primaryAccount;
    @JsonIgnore
    @OneToMany(mappedBy = "secondaryOwner")
    private Account secondaryAccount;

    public AccountHolder() {
    }

    /**
     * Class constructor specifying only address.
     **/
    public AccountHolder(String name, String password, String dni, LocalDate birth, Address address) {
        super(name,password);
        setDni(dni);
        setBirth(birth);
        setAddress(address);
    }

    /**
     * Class constructor specifying an address and one mailing address.
     **/
    public AccountHolder(String name, String password, String  dni, LocalDate birth, Address address, Address mailingAddress) {
        super(name, password);
        setDni(dni);
        setBirth(birth);
        setAddress(address);
        setMailingAddress(mailingAddress);
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
