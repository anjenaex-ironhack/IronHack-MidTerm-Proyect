package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.Repository.SavingRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.SavingDTO;
import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.Checking;
import com.ironhack.Midtem.Project.model.CreditCard;
import com.ironhack.Midtem.Project.model.Saving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class SavingService {

    @Autowired
    private SavingRepository savingRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    //================================================
    //Post Methods
    //================================================

    public void createSavingAccount(SavingDTO savingDTO) {

        Money balance = new Money(savingDTO.getBalanceAmount(), savingDTO.getBalanceCurrency());
        AccountHolder primaryOwner = accountHolderRepository.findById(savingDTO.getPrimaryOwnerId()).get();

        String secretKey = savingDTO.getSecretKey();
        Money minimumBalance = new Money(savingDTO.getMinimumBalanceAmount(), savingDTO.getMinimumBalanceCurrency());
        BigDecimal interestRate = savingDTO.getInterestRate();
        String statusString = savingDTO.getStatus().toString().toUpperCase();
        Status status = Status.valueOf(statusString);

        Saving saving;

        if(savingDTO.getSecondaryOwnerId().isPresent()) {
            AccountHolder secondaryOwner = accountHolderRepository.findById(savingDTO.getSecondaryOwnerId().get()).get();
            saving =
                    new Saving(balance, primaryOwner, secondaryOwner,secretKey, minimumBalance, interestRate, status);
        }else{
            saving =
                    new Saving(balance, primaryOwner, secretKey, minimumBalance, interestRate, status);
        }

        savingRepository.save(saving);

    }

    //================================================
    //Automatic account management
    //================================================

    public void updateSavingInterest (String id, LocalDate updateDate) {

                    BigDecimal newBalanceAmount =
                            savingRepository.findById(Long.valueOf(id)).get().getBalance().getAmount()
                                    .multiply(savingRepository.findById(Long.valueOf(id)).get().getInterestRate());
                    Money newBalance = new Money(newBalanceAmount);

                    Saving saving = savingRepository.findById(Long.valueOf(id)).get();
                    saving.setBalance(newBalance);
                    saving.setUpdateDate(updateDate);
                    savingRepository.save(saving);

    }
}
