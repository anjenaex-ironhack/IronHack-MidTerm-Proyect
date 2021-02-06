package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.utils.Money;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account{

    private String secretKey;
    private Status status;

    public StudentChecking(String secretKey, Status status) {
        this.secretKey = secretKey;
        this.status = status;
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner, Optional<AccountHolder> secondaryOwner, String secretKey, Status status) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
