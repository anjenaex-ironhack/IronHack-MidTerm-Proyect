package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.AccountRepository;
import com.ironhack.Midtem.Project.Repository.CreditCardRepository;
import com.ironhack.Midtem.Project.Repository.SavingRepository;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.controller.dto.BalanceDTO;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.CreditCard;
import com.ironhack.Midtem.Project.model.Saving;
import com.ironhack.Midtem.Project.model.StudentChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SavingRepository savingRepository;
    @Autowired
    private SavingService savingService;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private CreditCardService creditCardService;


    //================================================
    //Get Methods
    //================================================

    public List<Account> getAccountsByPrimaryOwnerOrSecondaryOwner(String name) {

        if (accountRepository.findByPrimaryOwnerNameOrSecondaryOwnerName(name, name).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else {
            return accountRepository.findByPrimaryOwnerNameOrSecondaryOwnerName(name, name);
        }

    }

    public List<Account> getAccountsByName(Optional<String> name) {
        if (name.isPresent()) {

            return getAccountsByPrimaryOwnerOrSecondaryOwner(name.get());

        } else {

            throw new IllegalArgumentException("yo need at least one name");

        }
    }

    //TODO: porque no me pilla los optionales
    public List<Account> getAccountsByDni(Optional<String> dni) {
        if (dni.isPresent()) {

            return getAccountsByPrimaryOwnerDniOrSecondaryOwnerDni(dni.get());

        } else {

            throw new IllegalArgumentException("yo need at least one DNI");

        }
    }

    public List<Account> getAccountsByPrimaryOwnerDniOrSecondaryOwnerDni(String dniLowerCase) {

        String dni = dniLowerCase.toUpperCase();

        if (accountRepository.findByPrimaryOwnerDniOrSecondaryOwnerDni(dni, dni).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else {
            return accountRepository.findByPrimaryOwnerDniOrSecondaryOwnerDni(dni, dni);
        }

    }

    public Money getBalanceById(String id) {

        if (accountRepository.findById(Long.valueOf(id)).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id " + id + "not found");
        } else {
            Account account = accountRepository.findById(Long.valueOf(id)).get();
            if (account instanceof Saving) {
                LocalDate updateDate = savingRepository.findById(Long.valueOf(id)).get().getUpdateDate();
                updateInterest(id, updateDate);
            }else if (account instanceof CreditCard){
                LocalDate updateDate = creditCardRepository.findById(Long.valueOf(id)).get().getUpdateDate();
                updateInterest(id, updateDate);
            }
            return accountRepository.findById(Long.valueOf(id)).get().getBalance();
        }

    }

    //================================================
    //Patch Methods
    //================================================

    public void updateBalance(String id, BalanceDTO balanceDTO) {

        Money balance = new Money(balanceDTO.getAmount(), balanceDTO.getCurrency());
        Optional<Account> account = accountRepository.findById(Long.valueOf(id));

        if (account.isPresent()) {
            try {
                account.get().setBalance(balance);
                accountRepository.save(account.get());
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Select a correct balance");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id " + id + "not found");
        }
    }

    //================================================
    //Automatic account management
    //================================================

    public void updateInterest(String id, LocalDate updateDate) {

        if (accountRepository.findById(Long.valueOf(id)).isPresent()) {

            LocalDate now = LocalDate.now();

            if (now.isEqual(updateDate) || now.isAfter(updateDate)) {

                if (accountRepository.findById(Long.valueOf(id)).get() instanceof Saving) {
                    savingService.updateSavingInterest(id, updateDate);
                } else if (accountRepository.findById(Long.valueOf(id)).get() instanceof CreditCard) {
                    creditCardService.updateCreditCardInterest(id, updateDate);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect type of date into updateDate");
                }
            }
        }
    }

}
