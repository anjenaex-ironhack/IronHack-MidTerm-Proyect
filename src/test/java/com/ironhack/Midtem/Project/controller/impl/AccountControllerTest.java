package com.ironhack.Midtem.Project.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.*;
import com.ironhack.Midtem.Project.repository.AccountHolderRepository;
import com.ironhack.Midtem.Project.repository.AccountRepository;
import com.ironhack.Midtem.Project.repository.AddressRepository;
import com.ironhack.Midtem.Project.repository.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoleRepository roleRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Money balance = new Money(new BigDecimal("1000"));
        Address address1 = new Address ("chiclana 1", 2400, "Ubeda", "España");
        addressRepository.save(address1);
        AccountHolder accountHolder1 = new AccountHolder("Antonio", LocalDate.of(1988, 12, 14), addressRepository.findAll().get(0), addressRepository.findAll().get(0));
        AccountHolder accountHolder2 = new AccountHolder("Jesus", LocalDate.of(1922, 12, 14), addressRepository.findAll().get(0), addressRepository.findAll().get(0));

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
    void getAllAccounts_getAll_AllAccounts() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Antonio"));
        assertTrue(result.getResponse().getContentAsString().contains("Jesus"));
    }

    @Test
    void getAccountById_accountId_AntonioAndJesus() throws Exception {
        List<Account> list = accountRepository.findAll();
        Long id = list.get(0).getId();
        MvcResult result = mockMvc.perform(get("/account/id/" + id)).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Antonio"));
        assertTrue(result.getResponse().getContentAsString().contains("Jesus"));
    }
    @Test
    void getAccountById_FailAccountId_False() throws Exception {
        List<Account> list = accountRepository.findAll();
        Long id = list.get(1).getId();
        MvcResult result = mockMvc.perform(get("/account/id/" + id)).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Antonio"));
        assertFalse(result.getResponse().getContentAsString().contains("Jesus"));
    }

}