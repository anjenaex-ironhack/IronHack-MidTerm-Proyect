package com.ironhack.Midtem.Project.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {


    @NotNull
    @NotEmpty
    private String name;

    public UserDTO() {
    }

    public UserDTO( @NotNull @NotEmpty String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
