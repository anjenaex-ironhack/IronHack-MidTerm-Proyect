package com.ironhack.Midtem.Project.Repository;

import com.ironhack.Midtem.Project.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
