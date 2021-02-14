package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.Repository.ThirdPartyRepository;
import com.ironhack.Midtem.Project.controller.dto.*;
import com.ironhack.Midtem.Project.controller.interfaces.IThirdPartyController;
import com.ironhack.Midtem.Project.service.impl.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ThirdPartyController /*implements IThirdPartyController*/ {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private ThirdPartyService thirdPartyService;
    @Autowired
    private AccountRepository accountRepository;

    //================================================
    //Post Methods
    //================================================

    @PostMapping("user/create/third-party")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCreditCard(@RequestBody @Valid ThirdPartyDTO thirdPartyDTO) {
        thirdPartyService.createThirdParty(thirdPartyDTO);
    }

    //================================================
    //Patch Methods
    //================================================

    @PatchMapping("user/third-party/send-money/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMoney(@PathVariable Long id, @RequestHeader String hashedKey,  @RequestBody @Valid AmountDTO amountDTO, @RequestParam String secretKey) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

//        if(accountRepository.findById(id).isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id " + id + " not found");
//        }else{
//            thirdPartyService.sendMoney(id, hashedKey, amountDTO, secretKey, userDetails);
//        }
    }

    @PatchMapping("user/third-party/receive-money/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void receiveMoney(@PathVariable String id, @RequestBody @Valid AccountIdDTO accountIdDTO, @RequestBody @Valid AmountDTO amountIdDTO) {

        if(thirdPartyRepository.findById(Long.valueOf(id)).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ThirdParty with id " + id + " not found");
        }else{
            thirdPartyService.getMoney(accountIdDTO, amountIdDTO);
        }
    }

}
