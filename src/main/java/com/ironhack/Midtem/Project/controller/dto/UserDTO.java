package com.ironhack.Midtem.Project.controller.dto;


import com.ironhack.Midtem.Project.model.Role;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

public class UserDTO {

    private String name;
    private List<Role> roleList;

    public UserDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
