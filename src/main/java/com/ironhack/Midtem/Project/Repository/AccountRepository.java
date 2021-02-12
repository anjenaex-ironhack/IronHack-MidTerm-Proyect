package com.ironhack.Midtem.Project.Repository;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    List<Account> findByPrimaryOwnerNameOrSecondaryOwnerName(String primaryOwner, String secondaryOwner);
    List<Account> findByPrimaryOwnerDniOrSecondaryOwnerDni(String primaryOwner, String secondaryOwner);



}
