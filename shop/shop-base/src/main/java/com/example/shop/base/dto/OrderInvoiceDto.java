/*
 * OrderInvoiceDto.java
 *
 * created at 2019-11-27 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


public class OrderInvoiceDto
{
    private Integer id;
    private UserInvoiceDto user;
    private LocalDateTime orderedOn;
    private LocalDateTime approvedOn;
    private Set<ProductOrderDto> products;
    private BigDecimal total;


    public Integer getId()
    {
        return id;
    }


    public UserInvoiceDto getUser()
    {
        return user;
    }


    public LocalDateTime getOrderedOn()
    {
        return orderedOn;
    }


    public LocalDateTime getApprovedOn()
    {
        return approvedOn;
    }


    public Set<ProductOrderDto> getProducts()
    {
        return products;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public void setUser(UserInvoiceDto user)
    {
        this.user = user;
    }


    public void setOrderedOn(LocalDateTime orderedOn)
    {
        this.orderedOn = orderedOn;
    }


    public void setApprovedOn(LocalDateTime approvedOn)
    {
        this.approvedOn = approvedOn;
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
