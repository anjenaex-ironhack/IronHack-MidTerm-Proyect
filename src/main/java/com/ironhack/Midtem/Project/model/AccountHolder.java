package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.enums.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
class AccountHolder extends User {

    private LocalDate birth;
    private String mailingAddress;

    @ManyToMany(mappedBy = "accountHolderList")
    private List<Address> addressList;

//  Un AccountHolder para muchas cuentas
    @OneToMany(mappedBy = "primaryOwner")
    private List<Account> primaryAccountList;

//  Un Account holder secudario, para muchas cuentas
    @OneToMany(mappedBy = "secondaryOwner")
    private List<Account> secondaryAccountList;

    public AccountHolder() {
    }

    public AccountHolder(LocalDate birth, String mailingAddress) {
        this.birth = birth;
        this.mailingAddress = mailingAddress;
    }

    public AccountHolder(String name, Role role, LocalDate birth, String mailingAddress) {
        super(name, role);
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
