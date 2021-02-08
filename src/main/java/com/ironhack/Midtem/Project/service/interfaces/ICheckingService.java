package com.ironhack.Midtem.Project.service.interfaces;

import com.ironhack.Midtem.Project.controller.dto.CheckingDTO;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.Checking;

public interface ICheckingService {

    Checking createCheckingAccountService (CheckingDTO checkingDTO);
}
