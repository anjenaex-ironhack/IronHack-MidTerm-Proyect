package com.ironhack.Midtem.Project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.Midtem.Project.Utils.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

    //  Many accounts for a user
    @OneToMany(mappedBy ="payer", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Transaction> transactionList;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "highest_daily_total_amount")),
            @AttributeOverride(name = "currency", column = @Column (name = "highest_daily_total_currency"))})
    private Money highestDailyTotal;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "daily_total_amount")),
            @AttributeOverride(name = "currency", column = @Column (name = "daily_total_currency"))})
    private Money dailyTotal = new Money(new BigDecimal("0"));
    private LocalDateTime checkingDay;

    public Account() {
    }

    public Account(Money balance, AccountHolder primaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
    }

    public Account(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        setCreationDate(LocalDate.now());
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

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Money getDailyTotal() {
        return dailyTotal;
    }

    public void setDailyTotal(Money dailyTotal) {
        this.dailyTotal = dailyTotal;
    }

    public Money getHighestDailyTotal() {
        return highestDailyTotal;
    }

    public void setHighestDailyTotal(Money highestDailyTotal) {
        this.highestDailyTotal = highestDailyTotal;
    }

    public LocalDateTime getCheckingDay() {
        return checkingDay;
    }

    public void setCheckingDay(LocalDateTime checkingDay) {
        this.checkingDay = checkingDay;
    }
}

