package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.Repository.CheckingRepository;
import com.ironhack.Midtem.Project.controller.dto.CheckingDTO;
import com.ironhack.Midtem.Project.service.impl.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CheckingController {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private CheckingService checkingService;

    //================================================
    //Post Methods
    //================================================

    @PostMapping("/create/checkingAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCheckingAccount(@RequestBody @Valid CheckingDTO checkingDTO) {
        checkingService.createCheckingAccount(checkingDTO);
    }


}
