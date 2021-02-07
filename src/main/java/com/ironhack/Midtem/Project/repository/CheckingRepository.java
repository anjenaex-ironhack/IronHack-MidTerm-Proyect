package com.ironhack.Midtem.Project.repository;

import com.ironhack.Midtem.Project.model.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {
}
