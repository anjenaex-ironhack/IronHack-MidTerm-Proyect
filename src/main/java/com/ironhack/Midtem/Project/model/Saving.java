package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.enums.Status;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account {

    private String secretKey;
    private BigDecimal interestRate;
    private Status status;

    @OneToOne
    @JoinColumn(name="credit_card")
    private CreditCard creditCard;

    public Saving() {
    }

    public Saving(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, String secretKey, BigDecimal interestRate, Status status, CreditCard creditCard) {
        super(primaryOwner, secondaryOwner, balance);
        this.secretKey = secretKey;
        this.interestRate = interestRate;
        this.status = status;
        this.creditCard = creditCard;
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

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
