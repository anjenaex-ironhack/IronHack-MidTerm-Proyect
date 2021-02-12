package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.Utils.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

    @Transient
    private static final Money PENALTY_FEE= new Money(new BigDecimal("40"));

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate creationDate;
    private LocalDate updateDate;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column (name = "balance_currency"))})
    private Money balance;
//  Many accounts for an user
    @ManyToOne
    @JoinColumn(name = "primary_owner")
    private AccountHolder primaryOwner;

//  Many accounts for a user
    @ManyToOne
    @JoinColumn(name = "secondary_owner")
    private AccountHolder secondaryOwner;

    public Account() {
    }

    public Account(Money balance, AccountHolder primaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
    }

    public Account(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        setCreationDate();
        this.updateDate = this.creationDate;
        setUpdateDate(this.updateDate);
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

//    public Account(LocalDate creationDate, Money balance, AccountHolder primaryOwner) {
//        this.creationDate = creationDate;
//        this.balance = balance;
//        this.primaryOwner = primaryOwner;
//    }
//
//    public Account(LocalDate creationDate, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
//        this.creationDate = creationDate;
//        this.balance = balance;
//        this.primaryOwner = primaryOwner;
//        this.secondaryOwner = secondaryOwner;
//    }

    public static Money getPenaltyFee() {
        return PENALTY_FEE;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        this.creationDate = LocalDate.now();
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate.plusYears(1L);
    }
}

