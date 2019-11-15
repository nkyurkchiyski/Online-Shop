/*
 * ProductDto.java
 *
 * created at 2019-11-11 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


import java.math.BigDecimal;


public class ProductDto
{
    private Integer id;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Integer quantity;


    public Integer getId()
    {
        return id;
    }


    public String getName()
    {
        return name;
    }


    public String getImageUrl()
    {
        return imageUrl;
    }


    public BigDecimal getPrice()
    {
        return price;
    }


    public Integer getQuantity()
    {
        return quantity;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }


    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }


    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

}
