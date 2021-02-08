package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.enums.Status;

import com.ironhack.Midtem.Project.Utils.Money;

public class CheckingDTO {

    private String secretKey;
    private Money minimumBalance;
    private Money monthlyMaintenanceFee;
    private Status status;


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

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
