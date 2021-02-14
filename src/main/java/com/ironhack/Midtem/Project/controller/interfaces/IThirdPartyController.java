package com.ironhack.Midtem.Project.controller.interfaces;

import com.ironhack.Midtem.Project.controller.dto.AccountIdDTO;
import com.ironhack.Midtem.Project.controller.dto.AmountDTO;
import com.ironhack.Midtem.Project.controller.dto.HashedKeyDTO;
import com.ironhack.Midtem.Project.controller.dto.ThirdPartyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

public interface IThirdPartyController {
    //================================================
    //Post Methods
    //================================================

    public void createCreditCard(ThirdPartyDTO thirdPartyDTO);

    //================================================
    //Post Methods
    //================================================

    public void sendMoney(String id, HashedKeyDTO HashedKeyDTO, Optional<AccountIdDTO> accountIdDTO,AmountDTO amountIdDTO);

    public void receiveMoney(String id, HashedKeyDTO HashedKeyDTO, Optional<AccountIdDTO> accountIdDTO, AmountDTO amountIdDTO);

}
