/*
 * Right.java
 *
 * created at 2019-11-11 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.model;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


public class Right implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cRightId")
    private Integer id;

    @Column(name = "cRightName")
    private String name;

    @ManyToMany(mappedBy = "rights", fetch = FetchType.LAZY)
    private Set<User> roles;


    public Right()
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


    public Set<User> getRoles()
    {
        return roles;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public void setRoles(Set<User> roles)
    {
        this.roles = roles;
    }

}
