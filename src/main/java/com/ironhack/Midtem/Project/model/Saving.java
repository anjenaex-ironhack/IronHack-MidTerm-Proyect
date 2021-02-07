package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.enums.Status;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account {

    private String secretKey;
    private BigDecimal interestRate;
    private Status status;

    public Saving() {
    }
}
