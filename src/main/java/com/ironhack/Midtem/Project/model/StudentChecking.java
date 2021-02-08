package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.enums.Status;

import javax.persistence.*;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account{

    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status;


    public StudentChecking() {
    }




}
