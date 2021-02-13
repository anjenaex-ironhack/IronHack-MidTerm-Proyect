package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.Repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.Repository.TransactionRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.AccountHolderDTO;
import com.ironhack.Midtem.Project.controller.dto.TransferDTO;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.Transaction;
import com.ironhack.Midtem.Project.service.impl.AccountService;
import com.ironhack.Midtem.Project.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    //================================================
    //Post Methods
    //================================================

    @PostMapping("/account/{id}/transfer-money")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMoneyAccountToAccount(@PathVariable String id, @RequestBody @Valid TransferDTO transferDTO){

        Optional<Account> account = accountRepository.findById(Long.valueOf(id));
        if(account.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender account with id " + id + "doesn't exist");
        }else{
            Optional<Account> beneficiaryAccount = accountRepository.findById(transferDTO.getBeneficiaryId());
            if(beneficiaryAccount.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiary account with id " + id + "doesn't exist");
            }else {
                if(beneficiaryAccount.get().getPrimaryOwner().getName().equals(transferDTO.getName()) ||
                        beneficiaryAccount.get().getSecondaryOwner().getName().equals(transferDTO.getName())) {
                    Money money = new Money(transferDTO.getTransferAmount(), transferDTO.getTransferCurrency());
                    transactionService.makeATransactionBetweenAccounts(id,transferDTO.getBeneficiaryId().toString(), money);
                }
            }

        }



    }
}
