package com.ironhack.Midtem.Project.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AccountIdDTO {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
