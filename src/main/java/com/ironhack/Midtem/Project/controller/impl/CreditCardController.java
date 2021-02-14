package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.controller.dto.CreditCardDTO;
import com.ironhack.Midtem.Project.controller.interfaces.ICreditCardController;
import com.ironhack.Midtem.Project.service.impl.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CreditCardController implements ICreditCardController {

    @Autowired
    private CreditCardController creditCardController;
    @Autowired
    private CreditCardService creditCardService;

    //================================================
    //Post Methods
    //================================================

    @PostMapping("/create/credit-card")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCreditCard(@RequestBody @Valid CreditCardDTO creditCardDTO) {
        creditCardService.createCreditCard(creditCardDTO);
    }

}
