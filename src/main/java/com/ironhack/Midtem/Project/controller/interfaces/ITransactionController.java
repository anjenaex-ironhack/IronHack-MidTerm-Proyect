package com.ironhack.Midtem.Project.controller.interfaces;

import com.ironhack.Midtem.Project.controller.dto.TransferDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

public interface ITransactionController {

    //================================================
    //Post Methods
    //================================================

    public void sendMoneyAccountToAccount(String id, TransferDTO transferDTO);

}
