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

@RestController
public class AccountHolderController {

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AccountHolderService accountHolderService;


    /**
     * Get Methods
     */
    @GetMapping("user/account-holders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAllAccountHolders (String id) {

        return accountHolderRepository.findAll();


    }

    @GetMapping("user/account-holder/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder getAccountHolderById (@PathVariable String id) {

            if (accountHolderRepository.findById(Long.valueOf(id)).isPresent()) {
                    return accountHolderRepository.findById(Long.valueOf(id)).get();
                }else{
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder not found");
                }

    }

    /**
     * Post Methods
     */

    @PostMapping("user/create/account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccountHolder (@RequestBody @Valid AccountHolderDTO accountHolderDTO) {
       accountHolderService.createAccountHolder(accountHolderDTO);
    }

}
