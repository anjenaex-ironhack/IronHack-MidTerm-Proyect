package com.ironhack.Midtem.Project.repository;

import com.ironhack.Midtem.Project.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByPrimaryOwnerName (String name);
    List<Account> findBySecondaryOwnerName (String name);
    List<Account> findByPrimaryOwnerNameAndSecondaryOwnerName(String name, String name2);

}
