package com.ironhack.Midtem.Project.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ThirdPartyDTO extends UserDTO{

    @NotNull
    @NotEmpty
    private String hashedKey;




    public ThirdPartyDTO( @NotNull @NotEmpty String name, @NotNull @NotEmpty String password) {
        super(name, password);
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
