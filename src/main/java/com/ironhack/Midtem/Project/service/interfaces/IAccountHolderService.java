package com.ironhack.Midtem.Project.service.interfaces;

import com.ironhack.Midtem.Project.controller.dto.AccountHolderDTO;
import com.ironhack.Midtem.Project.model.AccountHolder;

public interface IAccountHolderService {
    //================================================
    //Get Methods
    //================================================

    AccountHolder getAccountHolderByDni(String dni);

    AccountHolder getAccountHolderById(String id);

    //================================================
    //Post Methods
    //================================================

    void createAccountHolder(AccountHolderDTO accountHolderDTO);

}
