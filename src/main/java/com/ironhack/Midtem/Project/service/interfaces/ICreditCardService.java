package com.ironhack.Midtem.Project.service.interfaces;

import com.ironhack.Midtem.Project.Repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.Repository.CreditCardRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.CreditCardDTO;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public interface ICreditCardService {

    //================================================
    //Post Methods
    //================================================

    public void createCreditCard(CreditCardDTO creditCardDTO);

    //================================================
    //Automatic account management
    //================================================

    public void updateCreditCardInterest (String id, LocalDate updateDate);

}
