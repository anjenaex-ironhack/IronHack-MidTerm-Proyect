package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.Repository.CreditCardRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.CreditCardDTO;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    //================================================
    //Post Methods
    //================================================

    public void createCreditCard(CreditCardDTO creditCardDTO) {

        Money balance = new Money(creditCardDTO.getBalanceAmount(), creditCardDTO.getBalanceCurrency());
        AccountHolder primaryOwner = accountHolderRepository.findById(creditCardDTO.getPrimaryOwnerId()).get();
        Money creditLimit = new Money(creditCardDTO.getCreditLimitAmount(),creditCardDTO.getCreditLimitCurrency());
        BigDecimal interestRate = creditCardDTO.getInterestRate();

        CreditCard creditCard;

        if(creditCardDTO.getSecondaryOwnerId().isPresent()){
            AccountHolder secondaryOwner = accountHolderRepository.findById(creditCardDTO.getSecondaryOwnerId().get()).get();
            creditCard = new CreditCard (balance, primaryOwner, secondaryOwner, creditLimit,interestRate);
        }else{
            creditCard = new CreditCard(balance, primaryOwner, creditLimit,interestRate);
        }

        creditCardRepository.save(creditCard);
    }

    //================================================
    //Automatic account management
    //================================================

    public void updateCreditCardInterest (String id, LocalDate updateDate) {

        BigDecimal newBalanceAmount =
                creditCardRepository.findById(Long.valueOf(id)).get().getBalance().getAmount()
                        .multiply(creditCardRepository.findById(Long.valueOf(id)).get().getInterestRate());
        Money newBalance = new Money(newBalanceAmount);

        creditCardRepository.findById(Long.valueOf(id)).get().setBalance(newBalance);
        creditCardRepository.findById(Long.valueOf(id)).get().setUpdateDate(updateDate);

    }

}
