package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account{

    private String secretKey;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
                        @AttributeOverride(name = "currency", column = @Column (name = "minimum_balance_currency"))})
    private Money minimumBalance;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_fee_amount")),
                         @AttributeOverride(name = "currency", column = @Column (name = "monthly_maintenance_fee_currency"))})
    private Money monthlyMaintenanceFee;
    @Enumerated(EnumType.STRING)
    private Status status;


    public Checking() {
    }

    public Checking(String secretKey, Money minimumBalance, Money monthlyMaintenanceFee, Status status) {
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
    }

    public Checking(LocalDate creationDate, Money balance, AccountHolder primaryOwner, String secretKey, Money minimumBalance, Money monthlyMaintenanceFee, Status status) {
        super(creationDate, balance, primaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
    }

    public Checking(LocalDate creationDate, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Money minimumBalance, Money monthlyMaintenanceFee, Status status) {
        super(creationDate, balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
