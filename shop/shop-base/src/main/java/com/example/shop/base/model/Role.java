/*
 * Role.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.model;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "t_Roles")
public class Role implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cRoleId")
    private Integer id;

    @Column(name = "cRoleName")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "t_RoleRights", //
                    joinColumns = {@JoinColumn(name = "cRoleRightRoleId")}, //
                    inverseJoinColumns = {@JoinColumn(name = "cRoleRightRightId")}, //
                    foreignKey = @ForeignKey(name = "FK_RoleRights_Roles"), //
                    inverseForeignKey = @ForeignKey(name = "FK_RoleRights_Rights"))
    private Set<Role> rights;

    public Role()
    {
    }


    public Integer getId()
    {
        return id;
    }


    public String getName()
    {
        return name;
    }


    public Set<User> getUsers()
    {
        return users;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    public void addUser(User user)
    {
        this.users.add(user);
    }

    public void removeUser(User user)
    {
        this.users.remove(user);
    }

}
