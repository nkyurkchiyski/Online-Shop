/*
 * OrderDto.java
 *
 * created at 2019-11-11 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


import java.time.LocalDateTime;
import java.util.Set;

import com.example.shop.base.model.OrderStatus;


public class OrderDto
{
    private Integer id;
    private UserDto user;
    private OrderStatus status;
    private LocalDateTime orderedOn;
    private LocalDateTime deliveredOn;
    private Set<ProductOrderCreateDto> products;


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


    public Set<ProductOrderCreateDto> getProducts()
    {
        return products;
    }


    public void setProducts(Set<ProductOrderCreateDto> products)
    {
        this.products = products;
    }


    public Integer getId()
    {
        return id;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }

}
