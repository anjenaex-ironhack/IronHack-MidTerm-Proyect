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
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        AccountHolder accountHolder1 = new AccountHolder("Antonio", LocalDate.of(1988, 12, 14), "hola@buenas.es", addressRepository.findAll().get(0));
        AccountHolder accountHolder2 = new AccountHolder("Jesus", LocalDate.of(1922, 12, 14), "hola2@buenas.es", addressRepository.findAll().get(0));

        accountHolderRepository.saveAll(List.of(accountHolder1,accountHolder2));

        Role role1 = new Role("ACCOUNT_HOLDER", accountHolderRepository.findAll().get(0));
        roleRepository.save(role1);
        accountHolder1.setRoleList(roleRepository.findAll());

        accountHolderRepository.saveAll(List.of(accountHolder1,accountHolder2));

        Account account1 = new Account(LocalDate.of(2021,2,9), balance, accountHolderRepository.findAll().get(0), accountHolderRepository.findAll().get(1));
        Account account2 = new Account(LocalDate.of(1850,2,9), balance, accountHolderRepository.findAll().get(0));

        accountRepository.saveAll(List.of(account1,account2));

    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        roleRepository.deleteAll();
        accountHolderRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Test
    void getAccountById_correctId_Account1(){
        Money balance = new Money(new BigDecimal("1000"));
        Account account1 = new Account(LocalDate.of(1850,2,9), balance, accountHolderRepository.findAll().get(0));
        assertEquals(account1.getPrimaryOwner().getName(), accountService.getAccountById("1").getPrimaryOwner().getName());
        assertEquals(account1.getBalance().toString(), accountService.getAccountById("1").getBalance().toString());
    }
//    public Account getAccountById(String id) {
//        Optional<Account> account= accountRepository.findById(Long.valueOf(id));
//        if(account.isPresent()) {
//            return accountRepository.findById(Long.valueOf(id)).get();
//        }else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
//        }
//    }
}