package com.ironhack.Midtem.Project.controller.interfaces;

import com.ironhack.Midtem.Project.controller.dto.CheckingDTO;
import com.ironhack.Midtem.Project.model.Checking;

import java.util.List;
import java.util.Optional;

public interface ICheckingController {


    //========================GetMethods============================

    Optional<Checking> getCheckingAccountById (String id);
    List<Checking> getAllCheckingAccounts (String id);

    //========================PostMethods===========================

    Checking createCheckingAccount(CheckingDTO checkingAccount);

}
