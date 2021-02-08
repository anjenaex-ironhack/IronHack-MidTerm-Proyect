package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.enums.Status;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class StudentCheckingDTO {

    private String secretKey;
    private Status status;


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
