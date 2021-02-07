package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.enums.Status;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account{

    private String secretKey;
    private Money minimumBalance;
    private BigDecimal monthlyMaintenanceFee;
    private Status status;

    public Checking() {
    }

    public Checking(String secretKey, Money minimumBalance, BigDecimal monthlyMaintenanceFee, Status status) {
        setSecretKey(secretKey);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        setStatus(status);
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
