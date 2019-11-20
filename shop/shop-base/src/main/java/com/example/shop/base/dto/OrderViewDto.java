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

import com.example.shop.base.model.OrderStatus;


public class OrderViewDto
{
    private Integer id;
    private UserDto user;
    private OrderStatus status;
    private LocalDateTime orderedOn;
    private LocalDateTime deliveredOn;
    private BigDecimal total;


    public Integer getId()
    {
        return id;
    }


    public UserDto getUser()
    {
        return user;
    }


    public OrderStatus getStatus()
    {
        return status;
    }


    public LocalDateTime getOrderedOn()
    {
        return orderedOn;
    }


    public LocalDateTime getDeliveredOn()
    {
        return deliveredOn;
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


    public void setStatus(OrderStatus status)
    {
        this.status = status;
    }


    public void setOrderedOn(LocalDateTime orderedOn)
    {
        this.orderedOn = orderedOn;
    }


    public void setDeliveredOn(LocalDateTime deliveredOn)
    {
        this.deliveredOn = deliveredOn;
    }


    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }

}
