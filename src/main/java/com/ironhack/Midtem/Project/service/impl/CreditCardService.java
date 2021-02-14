package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.Repository.CreditCardRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.CreditCardDTO;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.CreditCard;
import com.ironhack.Midtem.Project.model.Saving;
import com.ironhack.Midtem.Project.service.interfaces.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CreditCardService implements ICreditCardService {

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

        BigDecimal interestRateDivided = creditCardRepository.findById(Long.valueOf(id)).get().getInterestRate().
                divide(new BigDecimal("12"), RoundingMode.UNNECESSARY);

        BigDecimal interestRate = interestRateDivided.add(new BigDecimal("1"));
        BigDecimal newBalanceAmount =
                creditCardRepository.findById(Long.valueOf(id)).get().getBalance().getAmount()
                        .multiply(interestRate);

        Money newBalance = new Money(newBalanceAmount);

        CreditCard creditCard = creditCardRepository.findById(Long.valueOf(id)).get();
        creditCard.setBalance(newBalance);
        creditCard.setUpdateDate(updateDate);
        creditCardRepository.save(creditCard);

    }

}
