package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.Utils.Money;

import java.math.BigDecimal;

public class AccountDTO {

    private static final Money PENALTY_FEE= new Money(new BigDecimal("40"));
    private long id;
    private AccountHolder primaryOwner;
    private AccountHolder secondaryOwner;
    private Money balance;

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

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
