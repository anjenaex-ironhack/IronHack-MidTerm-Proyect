package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.utils.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Money balance;
    private AccountHolder primaryOwner;
    private Optional<AccountHolder> secondaryOwner;

    public Account() {
    }

    public Account(Money balance, AccountHolder primaryOwner, Optional<AccountHolder> secondaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setSecondaryOwner(Optional<AccountHolder> secundaryOwner) {
        this.secondaryOwner = secundaryOwner;
    }
}
