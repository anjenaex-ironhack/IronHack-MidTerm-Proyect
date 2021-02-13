package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.Repository.CheckingRepository;
import com.ironhack.Midtem.Project.Repository.SavingRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.Checking;
import com.ironhack.Midtem.Project.model.Saving;
import com.ironhack.Midtem.Project.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private SavingRepository savingRepository;

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

    //================================================
    //Automatic account management
    //================================================
    public void makeATransaction(String senderAccountId, String beneficiaryAccountId, Money transactionbalance) {

        Optional<Account> sender = accountRepository.findById(Long.valueOf(senderAccountId));
        Optional<Account> beneficiary = accountRepository.findById(Long.valueOf(beneficiaryAccountId));

        if(sender.isPresent() && beneficiary.isPresent()){

            BigDecimal minimumBalance;
            if(sender.get() instanceof Checking){
                minimumBalance = checkingRepository.findById(Long.valueOf(senderAccountId)).get().getMinimumBalance().getAmount();
            }else if(sender.get() instanceof Saving){
                minimumBalance = savingRepository.findById(Long.valueOf(senderAccountId)).get().getMinimumBalance().getAmount();
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "only Checking and Saving Accounts have a minimum balance");
            }
            BigDecimal senderTotalAmount = sender.get().getBalance().getAmount();
            BigDecimal transactionAmount = transactionbalance.getAmount();
            LocalDateTime now = LocalDateTime.now();
            //TODO: implementar lastTransaction. para ello voya crear una lista de transacciones en una cuenta, y le metere el dato fecha de creacoin de esa cuenta en esta variable
            LocalDateTime lastTransaction = null;

            if(1>0){
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "can not create 2 transaction in less than a second");
            }

            if(senderTotalAmount.subtract(transactionAmount).compareTo(new BigDecimal("0")) < 0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the transaction amount can't be bigger than the total Account Amount");
            }
//            if(minimumBalance.subtract(transactionAmount).) {
//
//                Account senderAccount = accountRepository.findById(senderAccountId).get();
//                Account beneficiaryAccount = accountRepository.findById(beneficiaryAccountId).get();
//                Transaction transaction = new Transaction(senderAccount, beneficiaryAccount, amount);
//
//
//            }else {
//                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "You can't send an amount bigger than your minimum balance");
//            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "senderAccount or beneficiaryAccount not found");
        }

    }


}
