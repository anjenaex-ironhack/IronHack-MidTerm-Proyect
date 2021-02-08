package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.enums.Status;

import com.ironhack.Midtem.Project.Utils.Money;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class CheckingDTO {

    private String secretKey;
    @DecimalMin(value = "250")
    private BigDecimal minimumBalanceAmount;
    private String minimumBalanceCurrency;
    private BigDecimal monthlyMaintenanceFeeAmount = new BigDecimal("12");
    private String monthlyMaintenanceFeeCurrency;
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

    public String getMinimumBalanceCurrency() {
        return minimumBalanceCurrency;
    }

    public void setMinimumBalanceCurrency(String minimumBalanceCurrency) {
        this.minimumBalanceCurrency = minimumBalanceCurrency;
    }

    public BigDecimal getMonthlyMaintenanceFeeAmount() {
        return monthlyMaintenanceFeeAmount;
    }

    public void setMonthlyMaintenanceFeeAmount(BigDecimal monthlyMaintenanceFeeAmount) {
        this.monthlyMaintenanceFeeAmount = monthlyMaintenanceFeeAmount;
    }

    public String getMonthlyMaintenanceFeeCurrency() {
        return monthlyMaintenanceFeeCurrency;
    }

    public void setMonthlyMaintenanceFeeCurrency(String monthlyMaintenanceFeeCurrency) {
        this.monthlyMaintenanceFeeCurrency = monthlyMaintenanceFeeCurrency;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
