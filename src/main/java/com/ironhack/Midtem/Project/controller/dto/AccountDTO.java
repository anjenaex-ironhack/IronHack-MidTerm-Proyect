package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public class AccountDTO {

    private long id;
    @NotNull
    private long primaryOwnerId;
    private Optional<Long> secondaryOwnerId;

    public AccountDTO(@NotNull long primaryOwnerId, Optional<Long> secondaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public void setPrimaryOwnerId(long primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
    }

    public Optional<Long> getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public void setSecondaryOwnerId(Optional<Long> secondaryOwnerId) {
        this.secondaryOwnerId = secondaryOwnerId;
    }
}
