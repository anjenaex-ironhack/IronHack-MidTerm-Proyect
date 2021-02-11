package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.Repository.TransactionRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountHolderService{

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    //TODO: check with king of propierties do i need
    public Transaction createTransaction(Long senderAccountId, Long beneficiaryAccountId, Money amount) {

        if(accountRepository.findById(senderAccountId).isPresent() && accountRepository.findById(beneficiaryAccountId).isPresent()){
            BigDecimal minimumBalance = accountRepository.findById(senderAccountId).get().getBalance().getAmount();
            BigDecimal transactionAmount = amount.getAmount();
            if(minimumBalance.subtract(transactionAmount).equals(new BigDecimal("0"))) {

                Account senderAccount = accountRepository.findById(senderAccountId).get();
                Account beneficiaryAccount = accountRepository.findById(beneficiaryAccountId).get();
                Transaction transaction = new Transaction(senderAccount, beneficiaryAccount, amount);

                return transaction;
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "You can't send an amount bigger than your minimum balance");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "senderAccount or beneficiaryAccount not found");
        }


    }
}
