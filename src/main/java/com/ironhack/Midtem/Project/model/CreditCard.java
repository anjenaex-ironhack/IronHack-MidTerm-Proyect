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

    /**
     * Class constructor specifying a credit card account without a secondaryOwner
     **/
    public CreditCard(Money balance, AccountHolder primaryOwner, Money creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner);
        LocalDate creationDate = getCreationDate();
        setUpdateDate(creationDate);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    /**
     * Class constructor specifying a credit card account with a secondaryOwner
     **/
    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        setUpdateDate(LocalDate.now());
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
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
