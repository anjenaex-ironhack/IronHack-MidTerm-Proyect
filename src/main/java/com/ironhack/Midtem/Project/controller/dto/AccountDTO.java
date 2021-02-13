package com.ironhack.Midtem.Project.controller.dto;

import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.model.AccountHolder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

public class AccountDTO {

    private long id;
    //private Long primaryOwnerId
    //private Long secondaryOwnerId
    private AccountHolder primaryOwner;
    private Optional<AccountHolder> secondaryOwner;

    public AccountDTO(AccountHolder primaryOwner, Optional<AccountHolder> secondaryOwner) {
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public Optional<AccountHolder> getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(Optional<AccountHolder> secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }
}
