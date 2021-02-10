package com.ironhack.Midtem.Project.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.Account;
import com.ironhack.Midtem.Project.model.AccountHolder;
import com.ironhack.Midtem.Project.model.Address;
import com.ironhack.Midtem.Project.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

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

        accountHolderRepository.saveAll(List.of(accountHolder1,accountHolder2));

        Role role1 = new Role("ACCOUNT_HOLDER", accountHolderRepository.findAll().get(0));
        roleRepository.save(role1);
        accountHolder1.setRoleList(roleRepository.findAll());

        accountHolderRepository.saveAll(List.of(accountHolder1,accountHolder2));

        Account account1 = new Account(LocalDate.of(2021,2,9), balance, accountHolderRepository.findAll().get(0), accountHolderRepository.findAll().get(1));
        Account account2 = new Account(LocalDate.of(1850,2,9), balance, accountHolderRepository.findAll().get(0));
        Account account3 = new Account(LocalDate.of(1987,2,9), balance, accountHolderRepository.findAll().get(1), accountHolderRepository.findAll().get(0));


        accountRepository.saveAll(List.of(account1,account2,account3));
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        roleRepository.deleteAll();
        accountHolderRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Test
    void findByPrimaryOwnerNameAndSecondaryOwnerName_CorrectNames_correctAccount() {

        List<Account> accountList = accountRepository.findAll();

        assertTrue(accountList.get(0).getPrimaryOwner().getBirth().equals(
                accountRepository.findByPrimaryOwnerNameAndSecondaryOwnerName("Antonio", "Jesus").get(0).getPrimaryOwner().getBirth()));
        assertFalse(accountList.get(0).getPrimaryOwner().getBirth().equals(
                accountRepository.findByPrimaryOwnerNameAndSecondaryOwnerName("Jesus", "Antonio").get(0).getPrimaryOwner().getBirth()));
        assertNotEquals(accountList.get(2).getPrimaryOwner().getBirth(),
                accountRepository.findByPrimaryOwnerNameAndSecondaryOwnerName("Antonio", "Jesus").get(0).getPrimaryOwner().getBirth());

    }


    @Test
    void findByPrimaryOwnerName_Antonio_true() {
        List<Account> accountList = accountRepository.findAll();
        assertEquals(accountList.get(0).getPrimaryOwner().getBirth(),
                accountRepository.findByPrimaryOwnerName("Antonio").get(0).getPrimaryOwner().getBirth());

    }
    @Test
    void findByPrimaryOwnerName_Jesus_False() {
        List<Account> accountList = accountRepository.findAll();

        assertNotEquals(accountList.get(0).getPrimaryOwner().getBirth(),
                accountRepository.findByPrimaryOwnerName("Jesus").get(0).getPrimaryOwner().getBirth());
    }

    @Test
    void findBySecondaryOwnerName_Jesus_True() {
        List<Account> accountList = accountRepository.findAll();
        assertEquals(accountList.get(0).getPrimaryOwner().getBirth(),
                accountRepository.findBySecondaryOwnerName("Jesus").get(0).getPrimaryOwner().getBirth());
    }

    @Test
    void findBySecondaryOwnerName_Antonio_False() {
        List<Account> accountList = accountRepository.findAll();

        assertNotEquals(accountList.get(0).getPrimaryOwner().getBirth(),
                accountRepository.findBySecondaryOwnerName("Antonio").get(0).getPrimaryOwner().getBirth());
    }
}