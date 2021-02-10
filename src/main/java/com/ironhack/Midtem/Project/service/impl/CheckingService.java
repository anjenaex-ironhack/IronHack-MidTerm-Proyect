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

    //TODO: doesn't work
    public Checking createCheckingAccountService(CheckingDTO checkingDTO) {

        Money balance = new Money(checkingDTO.getBalance().getAmount(), checkingDTO.getBalance().getCurrency());
        Money minimumBalance = new Money (checkingDTO.getMinimumBalanceAmount(), checkingDTO.getMinimumBalanceCurrency());
        Money monthlyMaintenanceFee = new Money(checkingDTO.getMonthlyMaintenanceFeeAmount(), checkingDTO.getMonthlyMaintenanceFeeCurrency());

        Checking checking = null;

        if (checkingDTO.getSecondaryOwner().isEmpty()){
            checking = new Checking(balance, checkingDTO.getPrimaryOwner(), checkingDTO.getSecretKey(),
                                minimumBalance,monthlyMaintenanceFee,checkingDTO.getStatus());
        }else {
            checking = new Checking(balance, checkingDTO.getPrimaryOwner(), checkingDTO.getSecondaryOwner().get(), checkingDTO.getSecretKey(),
                    minimumBalance,monthlyMaintenanceFee,checkingDTO.getStatus());
        }

        return checking;
    }
}
