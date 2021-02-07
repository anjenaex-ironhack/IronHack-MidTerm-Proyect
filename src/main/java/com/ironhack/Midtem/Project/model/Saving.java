package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.enums.Status;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account{

    private String secretKey;
    private BigDecimal interestRate;
    private Status status;


    public Saving(String secretKey, BigDecimal interestRate, Status status) {
        this.secretKey = secretKey;
        this.interestRate = interestRate;
        this.status = status;
    }

    public Saving(Money balance, AccountHolder primaryOwner, Optional<AccountHolder> secondaryOwner, String secretKey, BigDecimal interestRate, Status status) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.interestRate = interestRate;
        this.status = status;
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
}
