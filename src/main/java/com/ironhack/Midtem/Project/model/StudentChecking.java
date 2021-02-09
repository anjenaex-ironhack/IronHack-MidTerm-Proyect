package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account{

    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status;


    public StudentChecking() {
    }

    public StudentChecking(String secretKey, Status status) {
        this.secretKey = secretKey;
        this.status = status;
    }

    public StudentChecking(LocalDate creationDate, Money balance, AccountHolder primaryOwner, String secretKey, Status status) {
        super(creationDate, balance, primaryOwner);
        this.secretKey = secretKey;
        this.status = status;
    }

    public StudentChecking(LocalDate creationDate, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Status status) {
        super(creationDate, balance, primaryOwner, secondaryOwner);
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
