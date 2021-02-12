package com.ironhack.Midtem.Project.Repository;

import com.ironhack.Midtem.Project.model.Role;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface RoleRepository extends JpaRepository<Role, Long> {
}
