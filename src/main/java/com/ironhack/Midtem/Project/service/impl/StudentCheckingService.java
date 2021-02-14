package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.Repository.StudentCheckingRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.CheckingDTO;
import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.StudentChecking;
import com.ironhack.Midtem.Project.service.interfaces.IStudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCheckingService implements IStudentCheckingService {

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    //================================================
    //Post Methods
    //================================================

    public void createStudentCheckingAccount(CheckingDTO checkingDTO){

        Money balance = new Money(checkingDTO.getBalanceAmount(), checkingDTO.getBalanceCurrency());
        AccountHolder primaryOwner = accountHolderRepository.findById(checkingDTO.getPrimaryOwnerId()).get();
        String secretKey = checkingDTO.getSecretKey();
        Status status;
        String statusString = checkingDTO.getStatus().toString().toUpperCase();
        status = Status.valueOf(statusString);

        StudentChecking studentChecking;

        if(checkingDTO.getSecondaryOwnerId().isPresent()){
            AccountHolder secondaryOwner = accountHolderRepository.findById(checkingDTO.getSecondaryOwnerId().get()).get();
            studentChecking = new StudentChecking(balance,primaryOwner,secondaryOwner, secretKey, status);

        }else{

            studentChecking =
                    new StudentChecking(balance,primaryOwner, secretKey, status);

        }
        studentCheckingRepository.save(studentChecking);
    }

}

