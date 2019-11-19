/*
 * ProductOrderDto.java
 *
 * created at 2019-11-19 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


public class ProductOrderDto
{
    private ProductDto product;
    private Integer quantity;


    public ProductDto getProduct()
    {
        return product;
    }


    public Integer getQuantity()
    {
        return quantity;
    }


    public void setProduct(ProductDto product)
    {
        this.product = product;
    }


    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

}
