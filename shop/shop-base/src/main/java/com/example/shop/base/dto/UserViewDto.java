/*
 * UserDto.java
 *
 * created at 2019-11-11 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


import java.time.LocalDate;


public class UserViewDto
{
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate createdAt;
    private boolean isActive;
    private boolean isAdmin;


    public Integer getId()
    {
        return id;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public boolean isAdmin()
    {
        return isAdmin;
    }


    public void setAdmin(boolean admin)
    {
        isAdmin = admin;
    }


    public String getEmail()
    {
        return email;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public String getLastName()
    {
        return lastName;
    }


    public LocalDate getCreatedAt()
    {
        return createdAt;
    }


    public boolean isActive()
    {
        return isActive;
    }


    public void setEmail(String email)
    {
        this.email = email;
    }


    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }


    public void setCreatedAt(LocalDate createdAt)
    {
        this.createdAt = createdAt;
    }


    public void setActive(boolean isActive)
    {
        this.isActive = isActive;
    }

}
