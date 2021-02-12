package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.model.Address;
import com.ironhack.Midtem.Project.model.User;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AccountHolderDTO extends User {

    @NotNull
    private LocalDate birth;
    @NotNull
    private Address address;
    private Address mailingAddress;

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
