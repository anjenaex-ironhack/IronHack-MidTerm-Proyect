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

    public List<Account> getAccountsByPrimaryOwnerOrSecondaryOwner(String name) {

        if(accountRepository.findByPrimaryOwnerNameOrSecondaryOwnerName(name, name).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else{
            return accountRepository.findByPrimaryOwnerNameOrSecondaryOwnerName(name, name);
        }

    }

    public List<Account> getAccountsByName (Optional<String> name) {
        if(name.isPresent()){

            return getAccountsByPrimaryOwnerOrSecondaryOwner(name.get());

        }else {

            throw new IllegalArgumentException("yo need at least one name");

        }
    }
    //TODO: porque no me pilla los optionales
    public List<Account> getAccountsByDni (Optional<String> dni) {
        if(dni.isPresent()){

            return getAccountsByPrimaryOwnerDniOrSecondaryOwnerDni(dni.get());

        }else {

            throw new IllegalArgumentException("yo need at least one DNI");

        }
    }

    public List<Account> getAccountsByPrimaryOwnerDniOrSecondaryOwnerDni(String dniLowerCase) {

        String dni = dniLowerCase.toUpperCase();

        if(accountRepository.findByPrimaryOwnerDniOrSecondaryOwnerDni(dni, dni).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else{
            return accountRepository.findByPrimaryOwnerDniOrSecondaryOwnerDni(dni, dni);
        }

    }








    //================================================
    //Post Methods
    //================================================

//    public
}
