package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.enums.Status;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account{

    private String secretKey;
    private Status status;

    public StudentChecking() {
    }
}
