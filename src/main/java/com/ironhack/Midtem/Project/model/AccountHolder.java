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
    @JoinColumn (name = "mailing_address")
    private Address mailingAddress;


//    @OneToMany(mappedBy = "primaryOwner")
//    private Account primaryAccount;
//
//    @OneToMany(mappedBy = "secondaryOwner")
//    private Account secondaryAccount;

    public AccountHolder() {
    }

    public AccountHolder(LocalDate birth, Address mailingAddress, Address address) {
        this.birth = birth;
        this.mailingAddress = mailingAddress;
        this.address = address;
    }

    public AccountHolder(String name, LocalDate birth, Address mailingAddress, Address address) {
        super(name);
        this.birth = birth;
        this.mailingAddress = mailingAddress;
        this.address = address;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
