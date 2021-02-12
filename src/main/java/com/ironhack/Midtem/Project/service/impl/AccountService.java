package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.BalanceDTO;
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


    //================================================
    //Get Methods
    //================================================

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

    public Money getBalanceById(String id){

        if(accountRepository.findById(Long.valueOf(id)).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id " + id + "not found");
        } else{
            return accountRepository.findById(Long.valueOf(id)).get().getBalance();
        }

    }

    //================================================
    //Patch Methods
    //================================================

    public void updateBalance (String id, BalanceDTO balanceDTO){

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id " + id + "not found");
        }
    }
}
