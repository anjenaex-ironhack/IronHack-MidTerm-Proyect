package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.Repository.CheckingRepository;
import com.ironhack.Midtem.Project.Repository.StudentCheckingRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.CheckingDTO;
import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.Checking;
import com.ironhack.Midtem.Project.service.interfaces.ICheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class CheckingService implements ICheckingService {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private StudentCheckingService studentCheckingService;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    //================================================
    //Post Methods
    //================================================

    public void createCheckingAccount(CheckingDTO checkingDTO) {

        Period period = Period.between(accountHolderRepository.findById(checkingDTO.getPrimaryOwnerId()).get().getBirth(), LocalDate.now());
        int age = period.getYears();

        if(age < 18) {

            studentCheckingService.createStudentCheckingAccount (checkingDTO);

        }else{

            Money balance = new Money(checkingDTO.getBalanceAmount(), checkingDTO.getBalanceCurrency());
            AccountHolder primaryOwner = accountHolderRepository.findById(checkingDTO.getPrimaryOwnerId()).get();
            String secretKey = checkingDTO.getSecretKey();
            Status status;
            String statusString = checkingDTO.getStatus().toString().toUpperCase();
            status = Status.valueOf(statusString);
            Money minimumBalance = new Money(checkingDTO.getMinimumBalanceAmount(), checkingDTO.getMinimumBalanceCurrency());
            Money monthlyMaintenanceFee = new Money(checkingDTO.getMonthlyMaintenanceFeeAmount(), checkingDTO.getMonthlyMaintenanceFeeCurrency());
            Checking checking;

            if(checkingDTO.getSecondaryOwnerId().isPresent()) {
                AccountHolder secondaryOwner = accountHolderRepository.findById(checkingDTO.getSecondaryOwnerId().get()).get();
                checking =
                        new Checking(balance, primaryOwner, secondaryOwner,secretKey, minimumBalance, monthlyMaintenanceFee, status);
            }else{
                checking =
                        new Checking(balance, primaryOwner, secretKey, minimumBalance, monthlyMaintenanceFee, status);
            }

            checkingRepository.save(checking);

        }
    }
}
