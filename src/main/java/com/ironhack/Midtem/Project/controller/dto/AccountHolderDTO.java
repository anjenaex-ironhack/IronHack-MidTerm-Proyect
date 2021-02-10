package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.model.Address;
import com.ironhack.Midtem.Project.model.Role;

import java.time.LocalDate;
import java.util.List;

public class AccountHolderDTO extends UserDTO{

    private LocalDate birth;
    private Address address;
    private Address mailingAddress;

    public AccountHolderDTO(String name, LocalDate birth, Address address, Address mailingAddress) {
        super(name);
        this.birth = birth;
        this.address = address;
        this.mailingAddress = mailingAddress;
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
