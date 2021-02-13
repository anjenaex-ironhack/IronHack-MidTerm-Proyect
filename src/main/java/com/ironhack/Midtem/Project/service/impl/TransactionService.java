package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.*;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private SavingRepository savingRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    //================================================
    //Automatic account management
    //================================================

    public Transaction createTransaction(Optional<Account> sender, Optional<Account> beneficiary, Money amount) {
        if(sender.isPresent() && beneficiary.isPresent()){
                Transaction transaction = new Transaction(sender.get(), beneficiary.get(), amount);
                return transaction;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "senderAccount or beneficiaryAccount not found");
        }
    }
    public void checkCreditLimit (Optional<Account> sender, Money transactionBalance){
        //if the account is a credit card, the transaction balance should be equal or lower than the credit limit
        if(sender.get() instanceof CreditCard){
            if(((CreditCard) sender.get()).getCreditLimit().getAmount().compareTo(transactionBalance.getAmount()) < 0){
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The transaction can't be higher than the credit limit");
            }
        }
    }

    public BigDecimal getMinimumBalance (Optional<Account> sender){
        BigDecimal minimumBalanceAmount = null;
        if(sender.get() instanceof Checking){
            if (checkingRepository.findById(sender.get().getId()).isPresent()){
                minimumBalanceAmount = checkingRepository.findById(sender.get().getId()).get().getMinimumBalance().getAmount();
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "checking account with id " + sender.get().getId() + " not found");
            }
        }

        if(sender.get() instanceof Saving){
            if(savingRepository.findById(sender.get().getId()).isPresent()){
                minimumBalanceAmount = savingRepository.findById(sender.get().getId()).get().getMinimumBalance().getAmount();
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "saving account with id " + sender.get().getId() + " not found");
            }
        }
        return minimumBalanceAmount;
    }

    public void makeATransactionBetweenAccounts(String senderAccountId, String beneficiaryAccountId, Money transactionBalance) {

        //Saving the account of the sender and beneficiary into variables
        Optional<Account> sender = accountRepository.findById(Long.valueOf(senderAccountId));
        Optional<Account> beneficiary = accountRepository.findById(Long.valueOf(beneficiaryAccountId));

        //Check that both accounts exist
        if(sender.isPresent() && beneficiary.isPresent()){

            //check if the account is a credit card and check the credit limit
            checkCreditLimit(sender, transactionBalance);

            //Get the minimum balance into checkings and saving accounts, to apply the penalty fee when necessary
            BigDecimal minimumBalanceAmount = getMinimumBalance(sender);

            //next methods are use to control the time flow into the creation of a transaction
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime lastTransactionTime;

            List<Transaction> transactionList = accountRepository.findById(Long.valueOf(senderAccountId)).get().getTransactionList();

            if (transactionList.size() > 0){
                lastTransactionTime = transactionList.get(transactionList.size() - 1).getTransactionTime();
            }else{
                LocalDate date = LocalDate.of(1988, 3,19);
                LocalTime time = LocalTime.MIDNIGHT;
                lastTransactionTime = LocalDateTime.of(date, time);
            }

            long timeBetweenTransactions = LocalDateTime.from(lastTransactionTime).until(now, ChronoUnit.SECONDS );

            if(timeBetweenTransactions < 1L){
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "can not create 2 transaction in less than a second");
            }

            //Get the sender balance amount and transaction amount value
            BigDecimal senderTotalAmount = sender.get().getBalance().getAmount();
            BigDecimal transactionAmount = transactionBalance.getAmount();

            //Checking that the sender have enough money to make the transfer
            if(senderTotalAmount.subtract(transactionAmount).compareTo(new BigDecimal("0")) < 0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the transaction amount can't be bigger than the total Account Amount");
            }

            //Exchange of the Money Amount, beneficiary wins
            beneficiary.get().getBalance().increaseAmount(transactionAmount);


            //adding new values into the repository of the beneficiary
            if(beneficiary.get() instanceof CreditCard){
                creditCardRepository.save((CreditCard) beneficiary.get());
            }else if( beneficiary.get() instanceof Checking){
                checkingRepository.save((Checking) beneficiary.get());
            }else if(beneficiary.get() instanceof StudentChecking){
                studentCheckingRepository.save((StudentChecking) beneficiary.get());
            }else if (beneficiary.get() instanceof  Saving){
                savingRepository.save((Saving) beneficiary.get());
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrong account type");
            }

            //Exchange of the Money Amount, sender lose
            sender.get().getBalance(). decreaseAmount(transactionAmount);
            //If the account is a checking or saving account, we apply the penalty fee when necessary
            if (sender.get() instanceof Checking || sender.get() instanceof Saving) {
                if(senderTotalAmount.compareTo(minimumBalanceAmount) < 0){
                    sender.get().getBalance().decreaseAmount(minimumBalanceAmount);
                }else{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "penalty fee could not be correctly added");
                }
            }

            //adding new values into the repository of the sender
            if(sender.get() instanceof CreditCard){
                creditCardRepository.save((CreditCard)sender.get());
            }else if( sender.get() instanceof Checking){
                checkingRepository.save((Checking) sender.get());
            }else if(sender.get() instanceof StudentChecking){
                studentCheckingRepository.save((StudentChecking) sender.get());
            }else if (sender.get() instanceof  Saving){
                savingRepository.save((Saving)sender.get());
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrong account type");
            }

            Transaction transaction = createTransaction(sender, beneficiary, transactionBalance);
            transactionRepository.save(transaction);

        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "senderAccount or beneficiaryAccount not found");
        }

    }


}
