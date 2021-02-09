package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.enums.Status;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.Account;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Currency;

public class CheckingDTO extends AccountDTO {

    private String secretKey;
    @DecimalMin(value = "250")
    private BigDecimal minimumBalanceAmount;
    private Currency minimumBalanceCurrency;
    private BigDecimal monthlyMaintenanceFeeAmount = new BigDecimal("12");
    private Currency monthlyMaintenanceFeeCurrency;
    private Status status;




    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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
