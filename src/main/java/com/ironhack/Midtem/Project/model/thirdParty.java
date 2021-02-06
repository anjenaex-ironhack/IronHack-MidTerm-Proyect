package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.enums.Role;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class thirdParty extends User{

    private String hashedKey;

    public thirdParty(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public thirdParty(String name, Role role, String hashedKey) {
        super(name, role);
        this.hashedKey = hashedKey;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
