package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account {

    private String secretKey;
    private LocalDate updateDate;
    private Money minimumBalance;
    private BigDecimal interestRate;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Saving() {
    }

    /**
     * Class constructor specifying a saving account without secondaryOwner
     **/
    public Saving(Money balance, AccountHolder primaryOwner, String secretKey, Money minimumBalance, BigDecimal interestRate, Status status) {
        super(balance, primaryOwner);
        setUpdateDate(LocalDate.now());
        setSecretKey(secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
        setStatus(status);
    }

    /**
     * Class constructor specifying a saving account with secondaryOwner
     **/
    public Saving(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Money minimumBalance, BigDecimal interestRate, Status status) {
        super(balance, primaryOwner, secondaryOwner);
        setUpdateDate(LocalDate.now());
        setSecretKey(secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
        setStatus(status);
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate.plusYears(1L);
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
}
