package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;

public class checkingDTO extends AccountDTO{

    private String secretKey;
    private BigDecimal balanceAmount;
    private Currency balanceCurrency;
    private BigDecimal minimumBalanceAmount;
    private Currency minimumBalanceCurrency;
    private BigDecimal monthlyMaintenanceFeeAmount;
    private Currency monthlyMaintenanceFeeCurrency;
    private Status status;

    public checkingDTO(AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(primaryOwner, secondaryOwner);
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Currency getBalanceCurrency() {
        return balanceCurrency;
    }

    public void setBalanceCurrency(Currency balanceCurrency) {
        this.balanceCurrency = balanceCurrency;
    }

    public BigDecimal getMinimumBalanceAmount() {
        return minimumBalanceAmount;
    }

    public void setMinimumBalanceAmount(BigDecimal minimumBalanceAmount) {
        this.minimumBalanceAmount = minimumBalanceAmount;
    }

    public Currency getMinimumBalanceCurrency() {
        return minimumBalanceCurrency;
    }

    public void setMinimumBalanceCurrency(Currency minimumBalanceCurrency) {
        this.minimumBalanceCurrency = minimumBalanceCurrency;
    }

    public BigDecimal getMonthlyMaintenanceFeeAmount() {
        return monthlyMaintenanceFeeAmount;
    }

    public void setMonthlyMaintenanceFeeAmount(BigDecimal monthlyMaintenanceFeeAmount) {
        this.monthlyMaintenanceFeeAmount = monthlyMaintenanceFeeAmount;
    }

    public Currency getMonthlyMaintenanceFeeCurrency() {
        return monthlyMaintenanceFeeCurrency;
    }

    public void setMonthlyMaintenanceFeeCurrency(Currency monthlyMaintenanceFeeCurrency) {
        this.monthlyMaintenanceFeeCurrency = monthlyMaintenanceFeeCurrency;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
