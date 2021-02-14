package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.Repository.ThirdPartyRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.AccountIdDTO;
import com.ironhack.Midtem.Project.controller.dto.AmountDTO;
import com.ironhack.Midtem.Project.controller.dto.ThirdPartyDTO;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.ThirdParty;
import com.ironhack.Midtem.Project.service.interfaces.IThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

@Service
public class ThirdPartyService implements IThirdPartyService {

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
    public void sendMoney(Optional<AccountIdDTO> accountIdDTO, AmountDTO amountDTO){

        if(accountIdDTO.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiary Account for ThirdParty sendMoney not found");
        }else{
            BigDecimal amount = amountDTO.getAmount();
            Currency currency = amountDTO.getCurrency();
            Account account = accountRepository.findById(accountIdDTO.get().getId()).get();
            BigDecimal newBalanceAmount = account.getBalance().increaseAmount(amount);
            Money newBalance = new Money (newBalanceAmount, currency);
            account.setBalance(newBalance);
            accountRepository.save(account);
        }

    }

    public void getMoney(Optional<AccountIdDTO> accountIdDTO, AmountDTO amountDTO){

        if(accountIdDTO.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiary Account for ThirdParty sendMoney not found");
        }else{
            BigDecimal amount = amountDTO.getAmount();
            Currency currency = amountDTO.getCurrency();
            Account account = accountRepository.findById(accountIdDTO.get().getId()).get();
            BigDecimal newBalanceAmount = account.getBalance().decreaseAmount(amount);
            Money newBalance = new Money (newBalanceAmount, currency);
            account.setBalance(newBalance);
            accountRepository.save(account);
            transactionService.createTransaction(Optional.of(account), new Money (amount, currency));
        }

    }
}
