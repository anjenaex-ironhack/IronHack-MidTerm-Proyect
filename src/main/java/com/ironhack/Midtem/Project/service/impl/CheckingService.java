package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.CheckingRepository;
import com.ironhack.Midtem.Project.Repository.StudentCheckingRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.checkingDTO;
import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.Checking;
import com.ironhack.Midtem.Project.model.StudentChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class CheckingService {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    //================================================
    //Post Methods
    //================================================

    public void createCheckingAccount(checkingDTO checkingDTO) {

        Money balance = new Money(checkingDTO.getBalanceAmount(), checkingDTO.getBalanceCurrency());
        AccountHolder primaryOwner = checkingDTO.getPrimaryOwner();
        AccountHolder secondaryOwner = checkingDTO.getSecondaryOwner();
        String secretKey = checkingDTO.getSecretKey();
        Status status = checkingDTO.getStatus();

        Period period = Period.between(checkingDTO.getPrimaryOwner().getBirth(), LocalDate.now());
        int age = period.getYears();

        if(age < 18) {

            if(Optional.of(checkingDTO.getSecondaryOwner()).isEmpty()){

                StudentChecking studentChecking = new StudentChecking(balance,primaryOwner,
                        secretKey, status);

                studentCheckingRepository.save(studentChecking);

            }else{

                StudentChecking studentChecking =
                        new StudentChecking(balance,primaryOwner, secondaryOwner, secretKey, status);

                studentCheckingRepository.save(studentChecking);

            }
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
