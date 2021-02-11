package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.StudentCheckingRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.StudentChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCheckingService {

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    //================================================
    //Post Methods
    //================================================
    public void createStudentCheckingAccount
            (Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Status status){

        if(Optional.of(secondaryOwner).isEmpty()){

            StudentChecking studentChecking =
                        new StudentChecking(balance,primaryOwner,secretKey, status);

            studentCheckingRepository.save(studentChecking);

        }else{

            StudentChecking studentChecking =
                    new StudentChecking(balance,primaryOwner, secondaryOwner, secretKey, status);

            studentCheckingRepository.save(studentChecking);

        }
    }

}

