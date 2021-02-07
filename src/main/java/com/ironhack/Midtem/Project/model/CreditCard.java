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


    public CreditCard() {
    }
}
