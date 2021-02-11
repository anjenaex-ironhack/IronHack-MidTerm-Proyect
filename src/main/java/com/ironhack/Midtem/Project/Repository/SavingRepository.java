package com.ironhack.Midtem.Project.Repository;

import com.ironhack.Midtem.Project.model.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Long> {
}
