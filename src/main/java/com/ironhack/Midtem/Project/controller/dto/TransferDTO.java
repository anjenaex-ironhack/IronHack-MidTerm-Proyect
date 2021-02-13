package com.ironhack.Midtem.Project.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

public class TransferDTO {

    @NotNull
    private Long BeneficiaryId;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private BigDecimal transferAmount;
    private Currency transferCurrency;

    public Long getBeneficiaryId() {
        return BeneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        BeneficiaryId = beneficiaryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Currency getTransferCurrency() {
        return transferCurrency;
    }

    public void setTransferCurrency(Currency transferCurrency) {
        this.transferCurrency = transferCurrency;
    }
}
