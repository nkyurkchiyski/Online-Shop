/*
 * UserProfileDto.java
 *
 * created at 2019-11-15 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


import java.time.LocalDateTime;


public class UserProfileDto
{
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private String address;


    public Integer getId()
    {
        return id;
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


    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }


    public String getAddress()
    {
        return address;
    }


    public void setId(Integer id)
    {
        this.id = id;
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


    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }


    public void setAddress(String address)
    {
        this.address = address;
    }

}
