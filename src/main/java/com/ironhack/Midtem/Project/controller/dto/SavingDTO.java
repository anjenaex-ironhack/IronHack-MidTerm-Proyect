package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.enums.Status;


import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class SavingDTO {

    @DecimalMin(value = "100", message = "the minimum balance is 100")
    private BigDecimal balanceAmount = new BigDecimal(1000);
    private String balanceCurrency;
    private String secretKey;
    @DecimalMax(value = "0.5", message = "the maximum value of interestRate is 0.5")
    private BigDecimal interestRate = new BigDecimal("0.0025");
    private Status status;



}

