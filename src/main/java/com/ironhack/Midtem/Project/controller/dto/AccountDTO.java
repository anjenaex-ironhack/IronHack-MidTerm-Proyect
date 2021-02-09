package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AccountDTO {

    private static final Money PENALTY_FEE= new Money(new BigDecimal("40"));
    private Money balance;
    @NotEmpty
    private AccountHolder primaryOwner;
    private AccountHolder secondaryOwner = null;

    public AccountDTO() {
    }

    public AccountDTO(Money balance, @NotEmpty AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public AccountDTO(Money balance, @NotEmpty AccountHolder primaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
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

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }
}
