package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.Utils.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account {

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column (name = "credit_limit_currency"))})
    private Money creditLimit;
    private BigDecimal interestRate;



    public CreditCard() {
    }

    public CreditCard(LocalDate creationDate, Money balance, AccountHolder primaryOwner, Money creditLimit, BigDecimal interestRate) {
        super(creationDate, balance, primaryOwner);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public CreditCard(LocalDate creationDate, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money creditLimit, BigDecimal interestRate) {
        super(creationDate, balance, primaryOwner, secondaryOwner);
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
