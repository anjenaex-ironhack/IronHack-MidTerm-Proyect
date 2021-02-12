package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Currency;

public class CheckingDTO extends AccountDTO{

    @NotNull
    @NotEmpty
    private String secretKey;
    @NotNull
    @DecimalMin(value = "250", message = "The minimum balance when you create a checking account is 250")
    private BigDecimal balanceAmount;
    private Currency balanceCurrency = Currency.getInstance("USD");
    @DecimalMin(value = "250", message = "The minimum balance when you create a checking account is 250")
    private BigDecimal minimumBalanceAmount;
    private Currency minimumBalanceCurrency = Currency.getInstance("USD");
    @DecimalMin(value = "12", message = "the monthly maintenance fee has to be 12")
    @DecimalMax(value = "12", message = "the monthly maintenance fee has to be 12")
    private BigDecimal monthlyMaintenanceFeeAmount = new BigDecimal("12");
    private Currency monthlyMaintenanceFeeCurrency = Currency.getInstance("USD");
    private Status status = Status.ACTIVE;

    public CheckingDTO(AccountHolder primaryOwner, AccountHolder secondaryOwner) {
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
