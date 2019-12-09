/*
 * OrderViewDto.java
 *
 * created at 2019-11-20 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;


public class OrderViewDto
{
    private Integer id;
    private UserDto user;
    private String status;
    private LocalDateTime orderedOn;
    private LocalDateTime approvedOn;
    private BigDecimal total;


    public Integer getId()
    {
        return id;
    }


    public UserDto getUser()
    {
        return user;
    }


    public String getStatus()
    {
        return status;
    }


    public LocalDateTime getOrderedOn()
    {
        return orderedOn;
    }


    public LocalDateTime getApprovedOn()
    {
        return approvedOn;
    }


    public BigDecimal getTotal()
    {
        return total;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public void setUser(UserDto user)
    {
        this.user = user;
    }


    public void setStatus(String status)
    {
        this.status = status;
    }


    public void setOrderedOn(LocalDateTime orderedOn)
    {
        this.orderedOn = orderedOn;
    }


    public void setApprovedOn(LocalDateTime approvedOn)
    {
        this.approvedOn = approvedOn;
    }


    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }

}
