package com.ironhack.Midtem.Project.controller.interfaces;

import com.ironhack.Midtem.Project.controller.dto.CheckingDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

public interface ICheckingInterface {

    //================================================
    //Post Methods
    //================================================

    public void createCheckingAccount(CheckingDTO checkingDTO);
}
