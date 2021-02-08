package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account {

    private String secretKey;
    private BigDecimal interestRate;
    @Enumerated(EnumType.STRING)
    private Status status;


    public Saving() {
    }



}
