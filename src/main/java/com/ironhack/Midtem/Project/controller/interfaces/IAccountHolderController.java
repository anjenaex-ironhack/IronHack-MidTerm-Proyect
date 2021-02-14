package com.ironhack.Midtem.Project.controller.interfaces;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.AccountHolderDTO;
import com.ironhack.Midtem.Project.controller.dto.BalanceDTO;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.AccountHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface IAccountHolderController {

    ///================================================
    //Get Methods
    //=================================================

    public List<AccountHolder> getAllAccountHolders (String id);
    public AccountHolder getAccountHolder (Optional<String> id, Optional<String> dni);
    //================================================
    //Get Methods
    //================================================

    public void createAccountHolder (@RequestBody @Valid AccountHolderDTO accountHolderDTO);

}
