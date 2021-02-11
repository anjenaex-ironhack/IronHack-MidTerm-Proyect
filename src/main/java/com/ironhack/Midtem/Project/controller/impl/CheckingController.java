package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.Repository.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckingController {

    @Autowired
    private CheckingRepository checkingRepository;

    //================================================
    //Post Methods
    //================================================

//    @PostMapping("/create/checkingAccount")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createCheckingAccount(@RequestBody checkingAccountDTO checkingAccountDTO) {
//        return createCheckingAccount(checkingAccountDTO);
//    }


}
