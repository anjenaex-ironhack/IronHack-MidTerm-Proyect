package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.CheckingDTO;
import com.ironhack.Midtem.Project.model.Checking;
import com.ironhack.Midtem.Project.repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.repository.CheckingRepository;
import com.ironhack.Midtem.Project.service.interfaces.ICheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;

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

        Checking checking = new Checking();


        return checkingRepository.save(checking);
    }
}
