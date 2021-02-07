package com.ironhack.Midtem.Project.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

    @Transient
    private static final Money PENALTY_FEE= new Money(new BigDecimal("40"));

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//  Many accounts for an user
    @ManyToOne
    @JoinColumn(name = "primary_owner")
    private AccountHolder primaryOwner;

//  Many accounts for a secondary user
    @ManyToOne
    @JoinColumn(name = "secondary_owner")
    private AccountHolder secondaryOwner;

    @OneToOne
    @JoinColumn(name = "balance")
    private Money balance;

    public Account() {
    }

    public Account(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance) {
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.balance = balance;
    }

    public static Money getPenaltyFee() {
        return PENALTY_FEE;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
