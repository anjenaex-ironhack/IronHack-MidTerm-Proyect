package com.ironhack.Midtem.Project.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.Address;
import com.ironhack.Midtem.Project.model.Role;
import com.ironhack.Midtem.Project.repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.repository.AccountRepository;
import com.ironhack.Midtem.Project.repository.AddressRepository;
import com.ironhack.Midtem.Project.repository.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoleRepository roleRepository;



    @BeforeEach
    void setUp() {


        Money balance = new Money(new BigDecimal("1000"));
        Address address1 = new Address ("chiclana 1", 2400, "Ubeda", "España");
        addressRepository.save(address1);
        AccountHolder accountHolder1 = new AccountHolder("Antonio", LocalDate.of(1988, 12, 14), addressRepository.findAll().get(0), addressRepository.findAll().get(0));
        AccountHolder accountHolder2 = new AccountHolder("Jesus", LocalDate.of(1922, 12, 14), addressRepository.findAll().get(0), addressRepository.findAll().get(0));
        AccountHolder accountHolder3 = new AccountHolder("Juan", LocalDate.of(1976, 12, 14), addressRepository.findAll().get(0), addressRepository.findAll().get(0));

        accountHolderRepository.saveAll(List.of(accountHolder1,accountHolder2));

        Role role1 = new Role("ACCOUNT_HOLDER", accountHolderRepository.findAll().get(0));
        roleRepository.save(role1);
        accountHolder1.setRoleList(roleRepository.findAll());

        accountHolderRepository.saveAll(List.of(accountHolder1,accountHolder2));

        Account account1 = new Account(LocalDate.of(2021,2,9), balance, accountHolderRepository.findAll().get(0), accountHolderRepository.findAll().get(1));
        Account account2 = new Account(LocalDate.of(1850,2,9), balance, accountHolderRepository.findAll().get(0));
        Account account3 = new Account(LocalDate.of(1850,2,9), balance, accountHolderRepository.findAll().get(1), accountHolderRepository.findAll().get(0));

        accountRepository.saveAll(List.of(account1,account2,account3));

    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        roleRepository.deleteAll();
        accountHolderRepository.deleteAll();
        addressRepository.deleteAll();
    }
//TODO: solo funciona si se activa individualmente
//    @Test
//    void getAccountById_correctId_Account1(){
//
//        List<Account> accountList = accountRepository.findAll();
//        Long result = accountService.getAccountById("1").getId();
//        Long idToCheck = accountList.get(0).getId();
//        assertEquals(idToCheck, result);
//        assertNotEquals(accountList.get(1).getId(), result);
//    }

    @Test
    void getAccountById_incorrectId_ResponseStatusExceptionNotFound(){

        assertThrows(ResponseStatusException.class, () ->accountService.getAccountById("100"));

    }
    @Test
    void getAccountById_incorrectId_IllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () ->accountService.getAccountById("patata"));


    }
    @Test
    void getAccountsByPrimaryOwnerAndSecondaryOwner_AntonioAndJesus_account1() {
        Long result = accountService.getAccountsByPrimaryOwnerAndSecondaryOwner("Antonio","Jesus").get(0).getId();
        assertEquals(accountRepository.findAll().get(0).getId(), result);
        assertNotEquals(accountRepository.findAll().get(1).getId(), result);
    }
    @Test
    void getAccountsByPrimaryOwnerAndSecondaryOwner_FakeName_ResponseStatusException(){
        assertThrows(ResponseStatusException.class, ()->accountService.getAccountsByPrimaryOwnerAndSecondaryOwner("Adolfo Dominguez","Pepe"));
    }

    @Test
    void getAccountsByPrimaryOwner_Antonio_account1() {
        Long result = accountService.getAccountsByPrimaryOwner("Antonio").get(0).getId();
        assertEquals(accountRepository.findAll().get(0).getId(), result);
        assertNotEquals(accountRepository.findAll().get(2).getId(), result);
    }
    @Test
    void getAccountsByPrimaryOwner_FakeName_ResponseStatusException(){
        assertThrows(ResponseStatusException.class, ()->accountService.getAccountsByPrimaryOwner("Adolfo Dominguez"));
    }
    @Test
    void getAccountsBySecondaryOwner_Jesus_account1() {
        Long result = accountService.getAccountsBySecondaryOwner("Jesus").get(0).getId();
        assertEquals(accountRepository.findAll().get(0).getId(), result);
        assertNotEquals(accountRepository.findAll().get(2).getId(), result);
    }
    @Test
    void getAccountsBySecondaryOwner_FakeName_ResponseStatusException(){
        assertThrows(ResponseStatusException.class, ()->accountService.getAccountsBySecondaryOwner("Adolfo Dominguez"));
    }

    @Test
    void getBalanceById_Balance_1000AndUSD() {
        Money result = accountService.getBalanceById("1");
        assertEquals(accountRepository.findAll().get(0).getBalance(), result);

    }
    @Test
    void getBalanceById_FakeBalance_ResponseStatusException(){
        assertThrows(ResponseStatusException.class, ()->accountService.getBalanceById("125"));
    }


}