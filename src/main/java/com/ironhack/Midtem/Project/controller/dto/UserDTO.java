package com.ironhack.Midtem.Project.controller.dto;


import com.ironhack.Midtem.Project.model.Role;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

public class UserDTO {

    private String name;
    @Pattern(regexp = "FROZEN | ACTIVE", message = "The status should be FROZEN or ACTIVE")
    private Role role;
}
