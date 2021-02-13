package com.ironhack.Midtem.Project.model;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User{

    public Admin() {
    }

    /**
     * Class constructor specifying an admin.
     **/
    public Admin(String name, String password) {
        super(name, password);
    }

}
