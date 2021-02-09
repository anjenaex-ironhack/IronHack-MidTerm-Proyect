package com.ironhack.Midtem.Project.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User {

    private LocalDate birth;
    private String mailingAddress;

    @OneToOne
    @JoinColumn(name = "address")
    private Address address;

//  Un AccountHolder para muchas cuentas
    @OneToMany(mappedBy = "primaryOwner")
    private List<Account> primaryAccountList;

//  Un Account holder secundario, para muchas cuentas
    @OneToMany(mappedBy = "secondaryOwner")
    private List<Account> secondaryAccountList;

    public AccountHolder() {
    }


    public AccountHolder(String name, LocalDate birth, String mailingAddress, Address address, List<Account> primaryAccountList, List<Account> secondaryAccountList) {
        super(name);
        this.birth = birth;
        this.mailingAddress = mailingAddress;
        this.address = address;
        this.primaryAccountList = primaryAccountList;
        this.secondaryAccountList = secondaryAccountList;
    }

    public AccountHolder(String name, LocalDate birth, String mailingAddress, Address address) {
        super(name);
        this.birth = birth;
        this.mailingAddress = mailingAddress;
        this.address = address;
    }

    public AccountHolder(LocalDate birth, String mailingAddress) {
        this.birth = birth;
        this.mailingAddress = mailingAddress;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
