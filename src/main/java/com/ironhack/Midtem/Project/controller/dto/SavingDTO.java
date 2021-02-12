package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.AccountHolder;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class SavingDTO extends AccountDTO{

    @NotEmpty
    @NotNull
    private String secretKey;
    @DecimalMin(value = "100", message = "The minimum balance when you create a saving account is 100")
    @NotNull
    private BigDecimal balanceAmount;
    private Currency balanceCurrency = Currency.getInstance("USD");
    @DecimalMax(value = "1000", message = "The maximum balance when you create a saving account is 1000")
    @DecimalMin(value = "100", message = "The minimum balance when you create a saving account is 100")
    private BigDecimal minimumBalanceAmount = new BigDecimal("1000");
    private Currency minimumBalanceCurrency = Currency.getInstance("USD");
    @DecimalMax(value = "0.5", message = "The maximum interest rate when you create a saving account is 0.5")
    private BigDecimal interestRate = new BigDecimal("0.0025");
    private Status status = Status.ACTIVE;


    public SavingDTO(AccountHolder primaryOwner, AccountHolder secondaryOwner) {
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

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
