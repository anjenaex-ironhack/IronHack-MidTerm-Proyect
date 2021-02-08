package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account{

    private String secretKey;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
                        @AttributeOverride(name = "currency", column = @Column (name = "minimum_balance_currency"))})
    private Money minimumBalance;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_fee_amount")),
                         @AttributeOverride(name = "currency", column = @Column (name = "monthly_maintenance_fee_currency"))})
    private Money monthlyMaintenanceFee;
    @Enumerated(EnumType.STRING)
    private Status status;


    public Checking() {
    }


}
