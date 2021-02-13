package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.Repository.AddressRepository;
import com.ironhack.Midtem.Project.Repository.TransactionRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.AccountHolderDTO;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.Address;
import com.ironhack.Midtem.Project.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class AccountHolderService{

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AddressRepository addressRepository;


    //================================================
    //Get Methods
    //================================================
    public AccountHolder getAccountHolderByDni(String dni){

        if(accountHolderRepository.findByDni(dni).isPresent()){
            return accountHolderRepository.findByDni(dni).get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder with DNI " + dni + "Not found");
        }
    }

    public AccountHolder getAccountHolderById(String id){

        if(accountHolderRepository.findById(Long.valueOf(id)).isPresent()){
            return accountHolderRepository.findByDni(id).get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder with DNI " + id + "Not found");
        }
    }
    //================================================
    //Post Methods
    //================================================

    public void createAccountHolder (AccountHolderDTO accountHolderDTO){
        String dni = accountHolderDTO.getDni();
        String password = accountHolderDTO.getPassword();
        String name = accountHolderDTO.getName();
        LocalDate birth = accountHolderDTO.getBirth();
        Address address = addressRepository.findById(accountHolderDTO.getAddressId()).get();

        AccountHolder accountHolder;

        if(accountHolderDTO.getMailingAddressId().isPresent()){
            Address mailingAddress = addressRepository.findById(accountHolderDTO.getMailingAddressId().get()).get();
            accountHolder = new AccountHolder(name,password, dni, birth, address, mailingAddress);
        }else {
            accountHolder = new AccountHolder(name, password, dni, birth, address);
        }

        accountHolderRepository.save(accountHolder);
    }






}
