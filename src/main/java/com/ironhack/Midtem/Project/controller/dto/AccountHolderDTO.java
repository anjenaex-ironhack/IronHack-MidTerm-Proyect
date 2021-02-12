package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.model.Address;
import com.ironhack.Midtem.Project.model.User;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public class AccountHolderDTO extends User {

    @NotNull
    @NotEmpty
    private String dni;
    @NotNull
    private LocalDate birth;
    @NotNull
    private Long addressId;
    private Optional<Long> mailingAddressId;

    public AccountHolderDTO( String name, @NotNull LocalDate birth, @NotNull Long addressId, Optional<Long> mailingAddressId) {
        super( name);
        this.birth = birth;
        this.addressId = addressId;
        this.mailingAddressId = mailingAddressId;
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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Optional<Long> getMailingAddressId() {
        return mailingAddressId;
    }

    public void setMailingAddressId(Optional<Long> mailingAddressId) {
        this.mailingAddressId = mailingAddressId;
    }
}
