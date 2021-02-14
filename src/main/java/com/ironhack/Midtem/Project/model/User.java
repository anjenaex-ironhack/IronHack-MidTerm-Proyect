package com.ironhack.Midtem.Project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.Midtem.Project.model.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    //@JsonIgnore
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Role> roleList;

    public User() {
    }

    /**
     * Class constructor specifying an user
     **/
    public User(String name, String password) {
        setName(name);
        setPassword(password);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //TODO: Check this
    @JsonIgnore
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
