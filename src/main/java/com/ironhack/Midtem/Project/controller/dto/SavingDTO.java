package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.AccountHolder;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Currency;

public class SavingDTO extends AccountDTO{

    @NotEmpty
    private String secretKey;
    @DecimalMin(value = "250", message = "The minimum balance when you create a checking account is 250")
    private BigDecimal balanceAmount;
    private Currency balanceCurrency;
    @DecimalMin(value = "250", message = "The minimum balance when you create a checking account is 250")
    private BigDecimal minimumBalanceAmount;
    private Currency minimumBalanceCurrency;
    @DecimalMin(value = "12", message = "the monthly maintenance fee has to be 12")
    @DecimalMax(value = "12", message = "the monthly maintenance fee has to be 12")
    private BigDecimal monthlyMaintenanceFeeAmount = new BigDecimal("12");
    private Currency monthlyMaintenanceFeeCurrency;
    private Status status = Status.ACTIVE;


    public SavingDTO(AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(primaryOwner, secondaryOwner);
    }
}
