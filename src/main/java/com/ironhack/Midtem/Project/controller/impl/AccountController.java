package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.Repository.CheckingRepository;
import com.ironhack.Midtem.Project.Repository.StudentCheckingRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.Checking;
import com.ironhack.Midtem.Project.model.Saving;
import com.ironhack.Midtem.Project.model.StudentChecking;
import com.ironhack.Midtem.Project.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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

    @GetMapping("/accounts/name")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccountByName(@RequestParam Optional<String> primaryOwner, @RequestParam Optional<String> secondaryOwner){
        return accountService.getAccountsByName(primaryOwner,secondaryOwner);
    }

    @GetMapping("/account/balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceById(@PathVariable String id){
        return accountRepository.findById(Long.valueOf(id)).get().getBalance();
    }

    /**
     * Post Methods
     */

    @PostMapping("/account/create/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCheckingAccount(@RequestBody Checking checking){

       int age = LocalDateTime.now().getYear() - checking.getPrimaryOwner().getBirth().getYear();
        System.out.println(age);

       if(age < 18) {
           if(Optional.of(checking.getSecondaryOwner()).isEmpty()){
               StudentChecking studentChecking = new StudentChecking(checking.getBalance(),checking.getPrimaryOwner(),
                                checking.getSecretKey(), checking.getStatus());

               studentCheckingRepository.save(studentChecking);
               //TODO: make student abstrac and delete those repositories
               //accountRepository.save(studentChecking);
           }else{
               StudentChecking studentChecking = new StudentChecking(checking.getBalance(),checking.getPrimaryOwner(),
                                checking.getSecondaryOwner(), checking.getSecretKey(), checking.getStatus());
               studentCheckingRepository.save(studentChecking);
               //TODO: make student abstrac and delete those repositories
               //accountRepository.save(studentChecking);
           }
       }else{
           checkingRepository.save(checking);

       }

    }

    //TODO: i have to create the rest of accounts, but first i will finish with this one
//    @PostMapping("/account/create/saving")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createSavingAccount(@RequestBody Saving saving){
//        accountRepository.save(saving);
//    }

}
