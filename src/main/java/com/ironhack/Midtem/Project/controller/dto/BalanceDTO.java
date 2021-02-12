package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.Utils.Money;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Currency;

public class BalanceDTO {

    private BigDecimal amount;
    private Currency currency;

    public BalanceDTO() {
    }

    public BalanceDTO(BigDecimal amount) {
        this.amount = amount;
    }

    public BalanceDTO(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
