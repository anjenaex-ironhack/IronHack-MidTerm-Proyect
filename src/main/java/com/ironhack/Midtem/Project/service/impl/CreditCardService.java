package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.CreditCardRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.CreditCardDTO;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    //================================================
    //Post Methods
    //================================================

    public void createCreditCard(CreditCardDTO creditCardDTO) {

        Money balance = new Money(creditCardDTO.getBalanceAmount(), creditCardDTO.getBalanceCurrency());
        AccountHolder primaryOwner = creditCardDTO.getPrimaryOwner();
        AccountHolder secondaryOwner = creditCardDTO.getSecondaryOwner();
        Money creditLimit = new Money(creditCardDTO.getCreditLimitAmount(),creditCardDTO.getCreditLimitCurrency());
        BigDecimal interestRate = creditCardDTO.getInterestRate();

        CreditCard creditCard;

        //TODO: preguntar a xabi porque no funciona esto
        if(Optional.of(secondaryOwner).isEmpty()){
            creditCard = new CreditCard (balance, primaryOwner, creditLimit,interestRate);
        }else{
            creditCard = new CreditCard(balance, primaryOwner, secondaryOwner, creditLimit,interestRate);
        }

        creditCardRepository.save(creditCard);
    }

}
