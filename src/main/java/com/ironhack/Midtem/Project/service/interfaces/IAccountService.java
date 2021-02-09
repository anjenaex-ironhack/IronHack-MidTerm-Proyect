package com.ironhack.Midtem.Project.service.interfaces;

import com.ironhack.Midtem.Project.model.Account;

import java.util.Optional;

public interface IAccountService {

    //=============================================================
    //========================GetMethod============================
    //=============================================================

    Account getAccountById (String id);

}
