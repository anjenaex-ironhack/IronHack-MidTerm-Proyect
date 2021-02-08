package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.Utils.Money;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class CreditCardDTO {

    @DecimalMax(value = "100000")
    private BigDecimal creditLimitAmount = new BigDecimal("100");
    private String creditLimitCurrency;
    @DecimalMin(value ="0.1")
    private BigDecimal interestRate = new BigDecimal("0.2");

    public BigDecimal getCreditLimitAmount() {
        return creditLimitAmount;
    }

    public void setCreditLimitAmount(BigDecimal creditLimitAmount) {
        this.creditLimitAmount = creditLimitAmount;
    }

    public String getCreditLimitCurrency() {
        return creditLimitCurrency;
    }

    public void setCreditLimitCurrency(String creditLimitCurrency) {
        this.creditLimitCurrency = creditLimitCurrency;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
