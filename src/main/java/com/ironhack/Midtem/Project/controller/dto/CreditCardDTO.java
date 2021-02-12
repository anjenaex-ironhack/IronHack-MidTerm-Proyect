package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.AccountHolder;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

public class CreditCardDTO extends AccountDTO{


    @NotNull
    private BigDecimal balanceAmount;
    private Currency balanceCurrency = Currency.getInstance("USD");
    @DecimalMax(value = "100000", message = "the maximum credit limit is 100000")
    private BigDecimal creditLimitAmount = new BigDecimal("100");
    private Currency creditLimitCurrency = Currency.getInstance("USD");
    @DecimalMax(value = "0.2", message = "the maximum interestRate is 0.2")
    @DecimalMin(value = "0.1", message = "the minimum interestRate is 0.1")
    private BigDecimal interestRate = new BigDecimal("0.2");

    public CreditCardDTO(long primaryOwnerId, Optional<Long> secondaryOwnerId) {
        super(primaryOwnerId, secondaryOwnerId);
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

    public BigDecimal getCreditLimitAmount() {
        return creditLimitAmount;
    }

    public void setCreditLimitAmount(BigDecimal creditLimitAmount) {
        this.creditLimitAmount = creditLimitAmount;
    }

    public Currency getCreditLimitCurrency() {
        return creditLimitCurrency;
    }

    public void setCreditLimitCurrency(Currency creditLimitCurrency) {
        this.creditLimitCurrency = creditLimitCurrency;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
