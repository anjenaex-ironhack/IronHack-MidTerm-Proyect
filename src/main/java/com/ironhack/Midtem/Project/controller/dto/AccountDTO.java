package com.ironhack.Midtem.Project.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class AccountDTO {

    private static final Money PENALTY_FEE= new Money(new BigDecimal("40"));
    private LocalDate creationDate;
    private Money balance;

    private AccountHolder primaryOwner;
    private Optional<AccountHolder> secondaryOwner;

    public AccountDTO() {
    }

    public AccountDTO(Money balance, AccountHolder primaryOwner, Optional<AccountHolder> secondaryOwner) {
        setCreationDate();
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public AccountDTO(Money balance,  AccountHolder primaryOwner) {
        setCreationDate();
        this.balance = balance;
        this.primaryOwner = primaryOwner;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        this.creationDate = LocalDate.now();
    }

    public static Money getPenaltyFee() {
        return PENALTY_FEE;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public Optional<AccountHolder> getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwnerO(Optional<AccountHolder> secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }
}
