package com.ironhack.Midtem.Project.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {

    @Id
    private String name;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> userList;

    public Role() {
    }

    public Role(String name, List<User> userList) {
        this.name = name;
        this.userList = userList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
