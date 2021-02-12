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
    private LocalDate updateDate;



    public CreditCard() {
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, Money creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner);
        this.updateDate = getCreationDate();
        setUpdateDate(this.updateDate);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        setUpdateDate(LocalDate.now());
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

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate.plusMonths(1L);
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }
}
