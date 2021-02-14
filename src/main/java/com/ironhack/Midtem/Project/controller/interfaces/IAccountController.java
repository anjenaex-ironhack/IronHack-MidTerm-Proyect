package com.ironhack.Midtem.Project.controller.interfaces;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.BalanceDTO;
import com.ironhack.Midtem.Project.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountController {

    //================================================
    //Get Methods
    //================================================

    public List<Account> getAllAccounts();
    public List<Account> getAccountByName(Optional<String> name);
    public List<Account> getAccountByDni( Optional<String> dni);
    public Account getAccountById(String id);
    public Money getBalanceById(String id);

    //================================================
    //Patch Methods
    //================================================

    public void updateBalance (String id, BalanceDTO balanceDTO);

}
