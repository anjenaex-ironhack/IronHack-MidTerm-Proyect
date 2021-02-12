package com.ironhack.Midtem.Project.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User {

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

    public AccountHolder(String dni, String name, LocalDate birth, Address address, Address mailingAddress) {
        super(dni, name);
        this.birth = birth;
        this.address = address;
        this.mailingAddress = mailingAddress;
    }

    public AccountHolder(String dni, String name, LocalDate birth, Address address) {
        super(dni, name);
        this.birth = birth;
        this.address = address;
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
