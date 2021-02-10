package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.CheckingDTO;
import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.Checking;
import com.ironhack.Midtem.Project.repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.repository.CheckingRepository;
import com.ironhack.Midtem.Project.service.interfaces.ICheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;

@Service
public class CheckingService implements ICheckingService {

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private CheckingRepository checkingRepository;


    public Checking createCheckingAccountService(CheckingDTO checkingDTO) {

        Money balance = new Money(checkingDTO.getBalance().getAmount(), checkingDTO.getBalance().getCurrency());
        Money minimumBalance = new Money (checkingDTO.getMinimumBalanceAmount(), checkingDTO.getMinimumBalanceCurrency());
        Money monthlyMaintenanceFee = new Money(checkingDTO.getMonthlyMaintenanceFeeAmount(), checkingDTO.getMonthlyMaintenanceFeeCurrency());

        if (checkingDTO.getSecondaryOwner().isEmpty()){
            Checking checking = new Checking();
        }else {

        }

        return null;
    }
}
    public Checking( Money balance, AccountHolder primaryOwner, String secretKey, Money minimumBalance, Money monthlyMaintenanceFee, Status status) {
        super(creationDate, balance, primaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
    }

    public Checking(Money balance, AccountHolder primaryOwner, Optional<AccountHolder> secondaryOwner, String secretKey, Money minimumBalance, Money monthlyMaintenanceFee, Status status) {
        super(creationDate, balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
    }