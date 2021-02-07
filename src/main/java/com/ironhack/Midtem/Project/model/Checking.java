package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.enums.Status;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account{

    private String secretKey;
    @OneToOne
    @JoinColumn(name= "minimum_balance")
    private Money minimumBalance;
    @OneToOne
    @JoinColumn(name= "monthly_maintenance_fee")
    private Money monthlyMaintenanceFee;
    private Status status;

    public Checking() {
    }


}
