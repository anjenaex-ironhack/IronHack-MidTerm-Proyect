package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.Repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.controller.dto.AccountHolderDTO;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.service.impl.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountHolderController {

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AccountHolderService accountHolderService;


    ///================================================
    //Get Methods
    //=================================================

    @GetMapping("user/account-holders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAllAccountHolders (String id) {
        return accountHolderRepository.findAll();
    }

    @GetMapping("user/account-holder")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder getAccountHolder (@RequestParam Optional<String> id, @RequestParam Optional<String> dni) {

        if(id.isPresent()){
            return accountHolderService.getAccountHolderById(id.get());
        }else if(dni.isPresent()){
            return accountHolderService.getAccountHolderByDni(dni.get());
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insert a correct id or DNI");
        }


    }

    //================================================
    //Get Methods
    //================================================

    @PostMapping("user/create/account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccountHolder (@RequestBody @Valid AccountHolderDTO accountHolderDTO) {
       accountHolderService.createAccountHolder(accountHolderDTO);
    }

}
