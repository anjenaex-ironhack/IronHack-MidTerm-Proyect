package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    //TODO: esto en realidad no vale pa na
    public List<Account> getAccountsByName (Optional<String> primaryOwner, Optional<String> secondaryOwner) {
        if(primaryOwner.isPresent() && secondaryOwner.isPresent()){
            return getAccountsByPrimaryOwnerAndSecondaryOwner(primaryOwner.get(), secondaryOwner.get());
        }else if (primaryOwner.isPresent()) {
            return getAccountsByPrimaryOwner(primaryOwner.get());
        }else if (secondaryOwner.isPresent()){
            return getAccountsBySecondaryOwner(secondaryOwner.get());
        }else {
            throw new IllegalArgumentException("yo need at least one param");
        }
    }


    public List<Account> getAccountsByPrimaryOwnerAndSecondaryOwner(String primaryOwner, String secondaryOwner) {

        if(accountRepository.findByPrimaryOwnerNameAndSecondaryOwnerName(primaryOwner, secondaryOwner).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else{
            return accountRepository.findByPrimaryOwnerNameAndSecondaryOwnerName(primaryOwner, secondaryOwner);
        }

    }

    public List<Account> getAccountsByPrimaryOwner(String primaryOwner) {

        if(accountRepository.findByPrimaryOwnerName(primaryOwner).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else{
            return accountRepository.findByPrimaryOwnerName(primaryOwner);
        }
    }

    public List<Account> getAccountsBySecondaryOwner(String secondaryOwner) {
        if(accountRepository.findBySecondaryOwnerName(secondaryOwner).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else{
            return accountRepository.findBySecondaryOwnerName(secondaryOwner);
        }
    }


}
