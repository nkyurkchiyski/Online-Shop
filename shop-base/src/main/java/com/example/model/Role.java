/*
 * User.java
 *
 * created at 2019-10-22 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.model;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "t_Roles")
public class Role implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cRoleId")
    private Long id;

    @Column(name = "cRoleName")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    public Role()
    {
    }


    public Long getId()
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


    public void setId(Long id)
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

}
