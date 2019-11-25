/*
 * MessageDto.java
 *
 * created at 2019-11-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


public class MessageDto
{
    private boolean successful;
    private String description;


    public MessageDto(boolean successful, String description)
    {
        this.successful = successful;
        this.description = description;
    }


    public boolean isSuccessful()
    {
        return successful;
    }


    public void setSuccessful(boolean successful)
    {
        this.successful = successful;
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description)
    {
        this.description = description;
    }

}
