package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.enums.Role;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User{

    public Admin() {
    }

    public Admin(String name, Role role) {
        super(name, role);
    }

}
