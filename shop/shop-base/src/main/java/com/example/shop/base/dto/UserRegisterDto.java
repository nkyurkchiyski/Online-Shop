/*
 * UserRegisterDto.java
 *
 * created at 2019-11-11 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


public class UserRegisterDto
{
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;


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


    public String getPassword()
    {
        return password;
    }


    public String getConfirmPassword()
    {
        return confirmPassword;
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


    public void setPassword(String password)
    {
        this.password = password;
    }


    public void setConfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }

}
