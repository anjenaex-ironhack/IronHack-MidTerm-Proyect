package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.Repository.ThirdPartyRepository;
import com.ironhack.Midtem.Project.controller.dto.*;
import com.ironhack.Midtem.Project.controller.interfaces.IThirdPartyController;
import com.ironhack.Midtem.Project.service.impl.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ThirdPartyController implements IThirdPartyController {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private ThirdPartyService thirdPartyService;

    //================================================
    //Post Methods
    //================================================

    @PostMapping("user/create/third-party")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCreditCard(@RequestBody @Valid ThirdPartyDTO thirdPartyDTO) {
        thirdPartyService.createThirdParty(thirdPartyDTO);
    }

    //================================================
    //Post Methods
    //================================================

    @PostMapping("user/third-party/{id}/receive-money")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void receiveMoney(@PathVariable String id, @RequestHeader @Valid HashedKeyDTO HashedKeyDTO, @RequestBody @Valid Optional<AccountIdDTO> accountIdDTO, @RequestBody @Valid AmountDTO amountIdDTO) {

        if(thirdPartyRepository.findById(Long.valueOf(id)).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ThirdParty with id " + id + " not found");
        }else{
            thirdPartyService.getMoney(accountIdDTO, amountIdDTO);
        }
    }

    //================================================
    //Post Methods
    //================================================

    @PatchMapping("user/third-party/{id}/send-money")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendMoney(@PathVariable String id, @RequestHeader @Valid HashedKeyDTO HashedKeyDTO, @RequestBody @Valid Optional<AccountIdDTO> accountIdDTO, @RequestBody @Valid AmountDTO amountIdDTO) {

        if(thirdPartyRepository.findById(Long.valueOf(id)).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ThirdParty with id " + id + " not found");
        }else{
            thirdPartyService.sendMoney(accountIdDTO, amountIdDTO);
        }
    }

}
