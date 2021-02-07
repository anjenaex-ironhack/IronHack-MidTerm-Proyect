package com.ironhack.Midtem.Project.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
public class Account {

    @Transient
    private static final Money PENALTY_FEE= new Money(new BigDecimal("40"));

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//  Many accounts for an user
    @ManyToOne
    @JoinColumn(name = "primary_owner")
    private AccountHolder primaryOwner;

//  Many accounts for a secundary user
    @ManyToOne
    @JoinColumn(name = "secondary_owner")
    private AccountHolder secondaryOwner;

    @OneToOne
    @JoinColumn(name = "balance")
    private Money balance;

    public Account() {
    }


}
