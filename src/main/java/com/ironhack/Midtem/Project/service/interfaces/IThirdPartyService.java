package com.ironhack.Midtem.Project.service.interfaces;

import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.Repository.ThirdPartyRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.AccountIdDTO;
import com.ironhack.Midtem.Project.controller.dto.AmountDTO;
import com.ironhack.Midtem.Project.controller.dto.ThirdPartyDTO;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.ThirdParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;


public interface IThirdPartyService {

    //================================================
    //Post Methods
    //================================================
    public void createThirdParty(ThirdPartyDTO ThirdPartyDTO);

    //================================================
    //Patch Methods
    //================================================

    public void sendMoney(Optional<AccountIdDTO> accountIdDTO, AmountDTO amountDTO);
    public void getMoney(Optional<AccountIdDTO> accountIdDTO, AmountDTO amountDTO);
}
