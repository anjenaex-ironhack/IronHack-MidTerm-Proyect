package com.ironhack.Midtem.Project.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account{

    private Money creditLimit;
    private BigDecimal interestRate;


    public CreditCard(Money creditLimit, BigDecimal interestRate) {
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, Optional<AccountHolder> secondaryOwner, Money creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
