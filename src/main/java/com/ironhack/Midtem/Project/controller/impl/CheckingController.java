package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.controller.dto.CheckingDTO;
import com.ironhack.Midtem.Project.controller.interfaces.ICheckingController;
import com.ironhack.Midtem.Project.model.Checking;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CheckingController implements ICheckingController {


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



    public Checking createCheckingAccount(CheckingDTO checkingAccount) {
        return null;
    }
}
