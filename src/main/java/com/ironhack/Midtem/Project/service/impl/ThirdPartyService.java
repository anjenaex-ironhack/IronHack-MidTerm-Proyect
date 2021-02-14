package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.Repository.ThirdPartyRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.AccountDTO;
import com.ironhack.Midtem.Project.controller.dto.AccountIdDTO;
import com.ironhack.Midtem.Project.controller.dto.AmountDTO;
import com.ironhack.Midtem.Project.controller.dto.ThirdPartyDTO;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.ThirdParty;
import com.ironhack.Midtem.Project.service.interfaces.IThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

@Service
public class ThirdPartyService /*implements IThirdPartyService*/ {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionService transactionService;

    //================================================
    //Post Methods
    //================================================

    public void createThirdParty(ThirdPartyDTO ThirdPartyDTO){
        String name = ThirdPartyDTO.getName();
        String password = ThirdPartyDTO.getPassword();
        String hashedKey = ThirdPartyDTO.getHashedKey();

        ThirdParty thirdParty = new ThirdParty(name, password, hashedKey);
        thirdPartyRepository.save(thirdParty);
    }

    //================================================
    //Patch Methods
    //================================================

    public void sendMoney(Long id, AmountDTO amountDTO, String hashedKey, String secretKey, UserDetails userDetails){
        //comprobar que la hashedKey existe. if(userDetails.getUsername().equals(thirdPartyRepository.findByHashedKey(hashedKey).get().getUsername())) {
        //seguridad: comparar que la hashedkey se corresponde con la hashedkey del que se logeo
        //comprobar estatus activo, hacer con id
        //comprobar que la secretKey coincide con el id introducido
        //sumar dinero en la cuenta, la linea para convertir el string en currency es Currency.getInstance(amount.getCurrency())
    }

    public void getMoney(AccountIdDTO accountIdDTO, AmountDTO amountDTO){



    }
}
