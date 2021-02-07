package com.ironhack.Midtem.Project.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account {

    @OneToOne
    @JoinColumn(name= "credit_limit")
    private Money creditLimit;
    private BigDecimal interestRate;

    @OneToOne(mappedBy = "creditCard")
    private Checking checking;

    @OneToOne(mappedBy = "creditCard")
    private StudentChecking studentChecking;

    @OneToOne(mappedBy = "creditCard")
    private Saving saving;


    public CreditCard() {
    }

    public CreditCard(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Money creditLimit, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, balance);
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

    public Checking getChecking() {
        return checking;
    }

    public void setChecking(Checking checking) {
        this.checking = checking;
    }

    public StudentChecking getStudentChecking() {
        return studentChecking;
    }

    public void setStudentChecking(StudentChecking studentChecking) {
        this.studentChecking = studentChecking;
    }

    public Saving getSaving() {
        return saving;
    }

    public void setSaving(Saving saving) {
        this.saving = saving;
    }
}
