package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.CheckingRepository;
import com.ironhack.Midtem.Project.Repository.StudentCheckingRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.CheckingDTO;
import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.Checking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class CheckingService {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private StudentCheckingService studentCheckingService;

    //================================================
    //Post Methods
    //================================================

    public void createCheckingAccount(CheckingDTO checkingDTO) {

        Money balance = new Money(checkingDTO.getBalanceAmount(), checkingDTO.getBalanceCurrency());
        AccountHolder primaryOwner = checkingDTO.getPrimaryOwner();
        AccountHolder secondaryOwner = checkingDTO.getSecondaryOwner();
        String secretKey = checkingDTO.getSecretKey();
        Status status;
        String statusString = checkingDTO.getStatus().toString().toUpperCase();
        status = Status.valueOf(statusString);


        Period period = Period.between(checkingDTO.getPrimaryOwner().getBirth(), LocalDate.now());
        int age = period.getYears();

        if(age < 18) {

            studentCheckingService.createStudentCheckingAccount
                    (balance, primaryOwner, secondaryOwner, secretKey, status);

        }else{

            Money minimumBalance = new Money(checkingDTO.getMinimumBalanceAmount(), checkingDTO.getMinimumBalanceCurrency());
            Money monthlyMaintenanceFee = new Money(checkingDTO.getMonthlyMaintenanceFeeAmount(), checkingDTO.getMonthlyMaintenanceFeeCurrency());
            Checking checking;

            if(Optional.of(checkingDTO.getSecondaryOwner()).isEmpty()) {
                checking =
                        new Checking(balance, primaryOwner, secondaryOwner,secretKey, minimumBalance, monthlyMaintenanceFee, status);
            }else{
                checking =
                        new Checking(balance, primaryOwner, secondaryOwner,secretKey, minimumBalance, monthlyMaintenanceFee, status);
            }

            checkingRepository.save(checking);

        }
    }
}
