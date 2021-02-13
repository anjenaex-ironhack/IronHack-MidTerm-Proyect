package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.*;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
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

    public void checkCorrectTransactionTime(Optional<Account> sender){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastTransactionTime;

        List<Transaction> transactionList = accountRepository.findById(sender.get().getId()).get().getTransactionList();

        if (transactionList.size() > 0){
            lastTransactionTime = transactionList.get(transactionList.size() - 1).getTransactionTime();
        }else{
            LocalDate date = LocalDate.of(1988, 3,19);
            LocalTime time = LocalTime.MIDNIGHT;
            lastTransactionTime = LocalDateTime.of(date, time);
        }

        long timeBetweenTransactions = LocalDateTime.from(lastTransactionTime).until(now, ChronoUnit.SECONDS );

        if(timeBetweenTransactions < 1L){

            if(sender.get() instanceof Saving){
                ((Saving) sender.get()).setStatus(Status.FROZEN);
                savingRepository.save((Saving)sender.get());
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "can not create 2 transaction in less than a second " +
                        "the saving account with id " + sender.get().getId() + " will be FROZEN, contact with an admin to reopen it");
            }else if( sender.get() instanceof Checking){
                ((Checking) sender.get()).setStatus(Status.FROZEN);
                checkingRepository.save(((Checking) sender.get()));
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "can not create 2 transaction in less than a second " +
                        "the checking account with id " + sender.get().getId() + " will be FROZEN, contact with an admin to reopen it");
            }else if(sender.get() instanceof StudentChecking){
                ((StudentChecking) sender.get()).setStatus(Status.FROZEN);
                studentCheckingRepository.save(((StudentChecking) sender.get()));
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "can not create 2 transaction in less than a second " +
                        "the student checking account with id " + sender.get().getId() + " will be FROZEN, contact with an admin to reopen it");
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "can not create 2 transaction in less than a second");
            }

        }
    }

    public void checkActive(Optional<Account> sender, Optional<Account> beneficiary){
        if(sender.isEmpty() || beneficiary.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sender or beneficiary fail, before checking the status");
        }else{
            if(sender.get() instanceof Checking){
                if(((Checking) sender.get()).getStatus().equals(Status.FROZEN)){
                    throw new ResponseStatusException(HttpStatus.LOCKED, "the account is FROZEN");
                }
            }else if( sender.get() instanceof StudentChecking) {
                if(((StudentChecking) sender.get()).getStatus().equals(Status.FROZEN)){
                    throw new ResponseStatusException(HttpStatus.LOCKED, "the account is FROZEN");
                }
            }else if (sender.get() instanceof Saving ){
                if(((Saving) sender.get()).getStatus().equals(Status.FROZEN)){
                    throw new ResponseStatusException(HttpStatus.LOCKED, "the account is FROZEN");
                }
            }else if(beneficiary.get() instanceof Checking){
                if(((Checking) beneficiary.get()).getStatus().equals(Status.FROZEN)){
                    throw new ResponseStatusException(HttpStatus.LOCKED, "the account is FROZEN");
                }
            }else if (beneficiary.get() instanceof StudentChecking){
                if(((StudentChecking) beneficiary.get()).getStatus().equals(Status.FROZEN)){
                    throw new ResponseStatusException(HttpStatus.LOCKED, "the account is FROZEN");
                }
            }else if (beneficiary.get() instanceof Saving) {
                if(((Saving)beneficiary.get()).getStatus().equals(Status.FROZEN)){
                    throw new ResponseStatusException(HttpStatus.LOCKED, "the account is FROZEN");
                }
            }
        }
    }

    public void checkEnoughBalance(Optional<Account> sender, Money transactionBalance){
        BigDecimal senderTotalAmount = sender.get().getBalance().getAmount();
        BigDecimal transactionAmount = transactionBalance.getAmount();

        //Checking that the sender have enough money to make the transfer
        if(senderTotalAmount.subtract(transactionAmount).compareTo(new BigDecimal("0")) < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the transaction amount can't be bigger than the total Account Amount");
        }
    }

    public void addBeneficiaryAmount(Optional<Account> beneficiary, Money transactionBalance){

        BigDecimal transactionAmount = transactionBalance.getAmount();
        beneficiary.get().getBalance().increaseAmount(transactionAmount);

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
    }

    public void subtractSenderAmount(Optional<Account> sender, Money transactionBalance){

        BigDecimal transactionAmount = transactionBalance.getAmount();
        sender.get().getBalance(). decreaseAmount(transactionAmount);

        //If the account is a checking or saving account, we apply the penalty fee when necessary
        if (sender.get() instanceof Checking || sender.get() instanceof Saving){

            if(sender.get().getBalance().getAmount().compareTo(getMinimumBalance(sender)) < 0){
                try{
                    sender.get().getBalance().decreaseAmount(Account.getPenaltyFee());
                }catch (Exception e){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "penalty fee could not be correctly added");
                }
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
    }

    public void makeATransactionBetweenAccounts(String senderAccountId, String beneficiaryAccountId, Money transactionBalance) {

        //Saving the account of the sender and beneficiary into variables
        Optional<Account> sender = accountRepository.findById(Long.valueOf(senderAccountId));
        Optional<Account> beneficiary = accountRepository.findById(Long.valueOf(beneficiaryAccountId));

        //Check that both accounts exist
        if(sender.isPresent() && beneficiary.isPresent()){

            //Check if the account is active
            checkActive(sender,beneficiary);

            //check if the account is a credit card and check the credit limit
            checkCreditLimit(sender, transactionBalance);

            //Get the minimum balance into checkings and saving accounts, to apply the penalty fee when necessary
            BigDecimal minimumBalanceAmount = getMinimumBalance(sender);

            //next method are use to control the time flow into the creation of a transaction
            checkCorrectTransactionTime(sender);

            //Check the maximum total daily amount an account can send in a day
            checkHighestDailyTotal(sender.get(), transactionBalance);

            //Get the sender balance amount and transaction amount value
            checkEnoughBalance(sender, transactionBalance);

            //adding new values into the repository of the beneficiary
            addBeneficiaryAmount(beneficiary, transactionBalance);

            //Exchange of the Money Amount, sender lose
            subtractSenderAmount(sender, transactionBalance);

            Transaction transaction = createTransaction(sender, beneficiary, transactionBalance);
            transactionRepository.save(transaction);

        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "senderAccount or beneficiaryAccount not found");
        }

    }

    public void checkHighestDailyTotal (Account sender, Money transactionBalance){

        //first, we tell the function. if there is no checkingDay, make one and start now
        if(Optional.ofNullable(sender.getCheckingDay()).isEmpty()) {
            sender.setCheckingDay(LocalDateTime.now());
            accountRepository.save(sender);
        }

        //then we set the time limit
        LocalDateTime timeDeadLine = sender.getCheckingDay().plusDays(1);
        LocalDateTime now = LocalDateTime.now();

        //if the time is lower than the limit time then we can continue in the same day
        if(now.isBefore(timeDeadLine)){
            //we tell the function, if there is no highestDailyTotal, make one with the first transaction amount
            if(Optional.ofNullable(sender.getHighestDailyTotal()).isEmpty()){
                sender.getDailyTotal().increaseAmount(transactionBalance);
                accountRepository.save(sender);
            }else{

                BigDecimal highestDailyTotalMultiplied = sender.getHighestDailyTotal().getAmount().multiply(new BigDecimal("1.5"));
                BigDecimal dailyTotalAmount = sender.getDailyTotal().getAmount();

                //if the highestDailyTotal * 1.5 is lower than all your others transfer today + this one
                if(highestDailyTotalMultiplied.compareTo(dailyTotalAmount.add(transactionBalance.getAmount())) < 0) {
                    sender.getDailyTotal().increaseAmount(transactionBalance);
                    accountRepository.save(sender);
                }else{
                    if(sender instanceof Saving){
                        ((Saving) sender).setStatus(Status.FROZEN);
                        savingRepository.save((Saving)sender);
                        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "the dailyTotalAmount is bigger than the 150% of highestDailyTotal " +
                                "the saving account with id " + sender.getId() + " will be FROZEN, contact with an admin to reopen it");
                    }else if( sender instanceof Checking){
                        ((Checking) sender).setStatus(Status.FROZEN);
                        checkingRepository.save(((Checking) sender));
                        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "the dailyTotalAmount is bigger than the 150% of highestDailyTotal " +
                                "the checking account with id " + sender.getId() + " will be FROZEN, contact with an admin to reopen it");
                    }else if(sender instanceof StudentChecking){
                        ((StudentChecking) sender).setStatus(Status.FROZEN);
                        studentCheckingRepository.save(((StudentChecking) sender));
                        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "the dailyTotalAmount is bigger than the 150% of highestDailyTotal " +
                                "the student checking account with id " + sender.getId() + " will be FROZEN, contact with an admin to reopen it");
                    }else{
                        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "the dailyTotalAmount is bigger than the 150% of highestDailyTotal ");
                    }
                }
            }
        }else{
            //update the date for next time
            sender.setCheckingDay(now);
            accountRepository.save(sender);

            //create highestDailyTotal if there is no one
//            if(Optional.ofNullable(sender.getHighestDailyTotal()).isEmpty()){
                sender.setHighestDailyTotal(sender.getDailyTotal());
                accountRepository.save(sender);
//            }

            //update highestDailyTotal if dailyTotal is bigger
            if(sender.getHighestDailyTotal().getAmount().compareTo(sender.getDailyTotal().getAmount()) < 1){
                sender.setHighestDailyTotal(sender.getDailyTotal());
                accountRepository.save(sender);
            }

            //reset daily total
            sender.setDailyTotal(new Money(new BigDecimal("0")));
            sender.setDailyTotal(transactionBalance);
            accountRepository.save(sender);

        }

    }

}
