/*
 * UserLoginDto.java
 *
 * created at 2019-11-11 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


public class UserLoginDto
{
    private String email;
    private String password;


    public String getEmail()
    {
        return email;
    }


    public String getPassword()
    {
        return password;
    }


    public void setEmail(String email)
    {
        this.email = email;
    }


    public void setPassword(String password)
    {
        this.password = password;
    }

}
