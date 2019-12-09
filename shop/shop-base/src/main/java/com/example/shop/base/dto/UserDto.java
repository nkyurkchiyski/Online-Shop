/*
 * UserDto.java
 *
 * created at 2019-11-19 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


public class UserDto
{
    private Integer id;
    private String email;


    public Integer getId()
    {
        return id;
    }


    public String getEmail()
    {
        return email;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public void setEmail(String email)
    {
        this.email = email;
    }

}
