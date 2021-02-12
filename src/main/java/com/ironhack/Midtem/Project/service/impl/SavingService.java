package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingService {

    @Autowired
    private SavingRepository savingRepository;

    //================================================
    //Post Methods
    //================================================
//
//    public void createSavingAccount(SavingkingDTO SavingkingDTO) {
//
//        Money balance = new Money(checkingDTO.getBalanceAmount(), checkingDTO.getBalanceCurrency());
//        AccountHolder primaryOwner = checkingDTO.getPrimaryOwner();
//        AccountHolder secondaryOwner = checkingDTO.getSecondaryOwner();
//        String secretKey = checkingDTO.getSecretKey();
//        String statusString = checkingDTO.getStatus().toString().toUpperCase();
//        Status status = Status.valueOf(statusString);
//
//
//    }



}
