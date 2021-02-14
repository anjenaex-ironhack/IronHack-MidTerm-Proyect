package com.ironhack.Midtem.Project.service.interfaces;

import com.ironhack.Midtem.Project.Repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.Repository.SavingRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.SavingDTO;
import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.Saving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


public interface ISavingService {

    //================================================
    //Post Methods
    //================================================

    public void createSavingAccount(SavingDTO savingDTO);

    //================================================
    //Automatic account management
    //================================================

    public void updateSavingInterest (String id, LocalDate updateDate);

}
