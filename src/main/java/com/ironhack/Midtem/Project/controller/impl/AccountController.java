package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.controller.dto.AccountDTO;
import com.ironhack.Midtem.Project.controller.dto.AccountHolderDTO;
import com.ironhack.Midtem.Project.controller.interfaces.IAccountControler;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.Address;
import com.ironhack.Midtem.Project.model.Role;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.repository.AccountRepository;
import com.ironhack.Midtem.Project.repository.AddressRepository;
import com.ironhack.Midtem.Project.repository.RoleRepository;
import com.ironhack.Midtem.Project.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountService accountService;

    //=============================================================
    //========================GetMethods===========================
    //=============================================================

    /**
     * get All the Accounts
     * @return
     */
    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    /**
     * get an Account by Account id
     * @param id
     * @return
     */
    @GetMapping("/account/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable String id) {

            return accountService.getAccountById(id);

    }

    /**
     * get an Account by the primaryOwner, secondaryOwner or both
     * @param primaryOwner
     * @param secondaryOwner
     * @return
     */
    @GetMapping("/accounts/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccountsByName(@RequestParam Optional<String> primaryOwner, @RequestParam Optional<String> secondaryOwner) {

        return accountService.getAccountsByName(primaryOwner, secondaryOwner);

    }

    /**
     * Get the balance of an Account found by id
     * @param id
     * @return
     */
    @GetMapping("/account/balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceById(@PathVariable String id) {
        return accountService.getBalanceById(id);
    }

    //===============================================================
    //========================Post Methods===========================
    //===============================================================

    @PostMapping("/account/create/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolder (@RequestBody AccountHolderDTO accountHolderDTO){

        AccountHolder accountHolder = new AccountHolder(accountHolderDTO.getName(), accountHolderDTO.getBirth(),
                accountHolderDTO.getAddress(), accountHolderDTO.getMailingAddress());

        return accountHolderRepository.save(accountHolder);

    }


}
