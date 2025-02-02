package com.ironhack.Midtem.Project.Repository;

import com.ironhack.Midtem.Project.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

    Optional<AccountHolder> findById(Long id);
    Optional<AccountHolder> findByDni(String dni);

}
