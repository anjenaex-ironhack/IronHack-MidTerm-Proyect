package com.ironhack.Midtem.Project.service.interfaces;

import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.Repository.CreditCardRepository;
import com.ironhack.Midtem.Project.Repository.SavingRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.BalanceDTO;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.CreditCard;
import com.ironhack.Midtem.Project.model.Saving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface IAccountService {

    //================================================
    //Get Methods
    //================================================

    public List<Account> getAccountsByPrimaryOwnerOrSecondaryOwner(String name);

    public List<Account> getAccountsByName(Optional<String> name);

    public List<Account> getAccountsByDni(Optional<String> dni);
    public List<Account> getAccountsByPrimaryOwnerDniOrSecondaryOwnerDni(String dniLowerCase);
    public Money getBalanceById(String id);

    //================================================
    //Patch Methods
    //================================================

    public void updateBalance(String id, BalanceDTO balanceDTO);

    //================================================
    //Automatic account management
    //================================================

    public void updateInterest(String id, LocalDate updateDate);

}
