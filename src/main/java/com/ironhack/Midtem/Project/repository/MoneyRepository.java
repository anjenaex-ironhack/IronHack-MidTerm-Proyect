package com.ironhack.Midtem.Project.repository;

import com.ironhack.Midtem.Project.model.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRepository extends JpaRepository<Money, Long> {
}
