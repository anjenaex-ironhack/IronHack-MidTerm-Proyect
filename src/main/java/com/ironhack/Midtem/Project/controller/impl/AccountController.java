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
//    I coment this one coz i think it doesn't work
    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    @GetMapping("/account/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable String id){
        return accountRepository.findById(Long.valueOf(id)).get();
    }

    //TODO: Add a getAccountsByDNI and valid with a not null and a @regex dni pattern

    @GetMapping("/accounts/name")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccountByName(@RequestParam Optional<String> primaryOwner, @RequestParam Optional<String> secondaryOwner){
        return accountService.getAccountsByName(primaryOwner,secondaryOwner);
    }

    @GetMapping("/account/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceById(@PathVariable String id){
        return accountRepository.findById(Long.valueOf(id)).get().getBalance();
    }

    /**
     * Post Methods
     */
    //TODO move all this methods to the correct controller, for example. createCheckingAccount to checking
    @PostMapping("/account/create/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCheckingAccount(@RequestBody Checking checking){

       Period period = Period.between(checking.getPrimaryOwner().getBirth(), LocalDate.now());
       int age = period.getYears();
        System.out.println(age + "===========================================================================================");

       if(age < 18) {
           if(Optional.of(checking.getSecondaryOwner()).isEmpty()){
               StudentChecking studentChecking = new StudentChecking(checking.getBalance(),checking.getPrimaryOwner(),
                                checking.getSecretKey(), checking.getStatus());

               studentCheckingRepository.save(studentChecking);

           }else{
               StudentChecking studentChecking = new StudentChecking(checking.getBalance(),checking.getPrimaryOwner(),
                                checking.getSecondaryOwner(), checking.getSecretKey(), checking.getStatus());

               studentCheckingRepository.save(studentChecking);
           }
       }else{
           checkingRepository.save(checking);

       }

    }

    @PostMapping("/account/create/saving")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSavingAccount(@RequestBody Saving saving){
        savingRepository.save(saving);
    }

    @PostMapping("/account/create/credit-card")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCreditCardAccount(@RequestBody CreditCard creditCard){
        creditCardRepository.save(creditCard);
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
