package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.Repository.*;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.BalanceDTO;
import com.ironhack.Midtem.Project.model.*;
import com.ironhack.Midtem.Project.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;


    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private SavingRepository savingRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    /**
     * Get Methods
     */

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccountByName(@PathVariable Optional<String> name){
        return accountService.getAccountsByName(name);
    }

    @GetMapping("/accounts/dni/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccountByDni(@PathVariable Optional<String> dni){
        return accountService.getAccountsByDni(dni);
    }

    @GetMapping("/account/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable String id){
        return accountRepository.findById(Long.valueOf(id)).get();
    }
    @GetMapping("/account/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceById(@PathVariable String id){
        return accountRepository.findById(Long.valueOf(id)).get().getBalance();
    }


    /**
     * Patch Methods
     */

    @PatchMapping("/account/balance/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance (@PathVariable String id, @RequestBody BalanceDTO balanceDTO){

        Money balance = new Money(balanceDTO.getAmount(), balanceDTO.getCurrency());
        Optional<Account> account = accountRepository.findById(Long.valueOf(id));

        if(account.isPresent()){
            try{
                account.get().setBalance(balance);
                accountRepository.save(account.get());
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Select a correct balance");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

}
