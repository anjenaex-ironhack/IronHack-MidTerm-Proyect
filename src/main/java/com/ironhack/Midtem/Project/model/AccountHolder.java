package com.ironhack.Midtem.Project.model;

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


//    @OneToMany(mappedBy = "primaryOwner")
//    private Account primaryAccount;
//
//    @OneToMany(mappedBy = "secondaryOwner")
//    private Account secondaryAccount;

    public AccountHolder() {
    }

    public AccountHolder(String name, String password, String dni, LocalDate birth, Address address) {
        super(name,password);
        this.dni = dni;
        this.birth = birth;
        this.address = address;
    }

    public AccountHolder(String name, String password, String  dni, LocalDate birth, Address address, Address mailingAddress) {
        super(name, password);
        this.dni = dni;
        this.birth = birth;
        this.address = address;
        this.mailingAddress = mailingAddress;
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
