package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.repository.AccountRepository;
import com.ironhack.Midtem.Project.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;


    public Account getAccountById(String id) {
        Optional<Account> account= accountRepository.findById(Long.valueOf(id));
        if(account.isPresent()) {
            return accountRepository.findById(Long.valueOf(id)).get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }
}
