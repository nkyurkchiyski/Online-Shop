/*
 * CartDto.java
 *
 * created at 2019-11-19 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import com.example.shop.base.model.OrderStatus;


public class OrderDetailsDto
{
    private Integer id;
    private UserDto user;
    private OrderStatus status;
    private LocalDateTime orderedOn;
    private LocalDateTime deliveredOn;
    private Set<ProductOrderDto> products;
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


    public Set<ProductOrderDto> getProducts()
    {
        return products;
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


    public void setProducts(Set<ProductOrderDto> products)
    {
        this.products = products;
    }


    public BigDecimal getTotal()
    {
        if (this.total == null)
        {
            this.setTotal();
        }
        return this.total;
    }


    private void setTotal()
    {
        this.total = this.products.stream()//
                                  .map(x -> x.getProduct().getPrice().multiply(new BigDecimal(x.getQuantity())))
                                  .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

}
