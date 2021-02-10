package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.controller.dto.CheckingDTO;
import com.ironhack.Midtem.Project.controller.interfaces.ICheckingController;
import com.ironhack.Midtem.Project.model.Checking;
import com.ironhack.Midtem.Project.service.impl.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CheckingController{

    @Autowired
    private CheckingService checkingService;

    //=============================================================
    //========================GetMethod============================
    //=============================================================

    public Optional<Checking> getCheckingAccountById(String id) {
        return Optional.empty();
    }

    public List<Checking> getAllCheckingAccounts(String id) {
        return null;
    }

    //=============================================================
    //=======================PostMethod============================
    //=============================================================


    @PostMapping("/account/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking createCheckingAccount( CheckingDTO checkingAccount) {

        return checkingService.createCheckingAccountService(checkingAccount);
    }
}
