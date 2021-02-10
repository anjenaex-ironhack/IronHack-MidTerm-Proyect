package com.ironhack.Midtem.Project.controller.interfaces;

import com.ironhack.Midtem.Project.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountControler {

    //=============Get==================
    Account getAccountById (String id);
    //List<Account> getAccountsByName(Optional<String> primaryOwner, Optional<String> secondaryOwner);

}
