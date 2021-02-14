package com.ironhack.Midtem.Project.controller.impl;

import com.ironhack.Midtem.Project.Repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.Repository.TransactionRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.TransferDTO;
import com.ironhack.Midtem.Project.controller.interfaces.ITransactionController;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.service.impl.AccountService;
import com.ironhack.Midtem.Project.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class TransactionController implements ITransactionController {

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

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        Optional<Account> account = accountRepository.findById(Long.valueOf(id));
        //Check sender account exist
        if(account.isEmpty()) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender account with id " + id + " doesn't exist");

        }else{

            Optional<Account> beneficiaryAccount = accountRepository.findById(transferDTO.getBeneficiaryId());
            //Check beneficiary Account exist
            if(beneficiaryAccount.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiary account with id " + id + "doesn't exist");
            }
            //Check both accounts are different
            if(account.equals(beneficiaryAccount)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "unable to make a transaction to the own account");
            }
            //Check the beneficiary account has the same id and name
            Boolean checkIfPrimaryOwnerHasTheName = beneficiaryAccount.get().getPrimaryOwner().getName().equals(transferDTO.getName());
            Boolean checkIfSecondaryOwnerHasTheName = beneficiaryAccount.get().getSecondaryOwner().getName().equals(transferDTO.getName());

            if(checkIfPrimaryOwnerHasTheName || checkIfSecondaryOwnerHasTheName) {

                Money money = new Money(transferDTO.getTransferAmount(), transferDTO.getTransferCurrency());
                transactionService.makeATransaction(id,transferDTO.getBeneficiaryId().toString(), money);

            }else{

                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The account with id " + id + " doesn't have any user with the name " + transferDTO.getName());

            }

        }

    }
}
