package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.Repository.CheckingRepository;
import com.ironhack.Midtem.Project.Repository.SavingRepository;
import com.ironhack.Midtem.Project.controller.dto.CheckingDTO;
import com.ironhack.Midtem.Project.controller.dto.SavingDTO;
import com.ironhack.Midtem.Project.controller.interfaces.ISavingInterface;
import com.ironhack.Midtem.Project.service.impl.CheckingService;
import com.ironhack.Midtem.Project.service.impl.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SavingController implements ISavingInterface {

    @Autowired
    private SavingRepository savingRepository;
    @Autowired
    private SavingService savingService;

    //================================================
    //Post Methods
    //================================================

    @PostMapping("/create/saving-account")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCheckingAccount(@RequestBody @Valid SavingDTO savingDTO) {
        savingService.createSavingAccount(savingDTO);
    }



}
