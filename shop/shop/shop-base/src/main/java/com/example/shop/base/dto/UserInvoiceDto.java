/*
 * UserInvoiceDto.java
 *
 * created at 2019-11-28 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


public class UserInvoiceDto
{
    private String email;
    private String firstName;
    private String lastName;
    private String address;


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


    public String getAddress()
    {
        return address;
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


    public void setAddress(String address)
    {
        this.address = address;
    }

}
