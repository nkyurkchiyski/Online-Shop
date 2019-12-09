/*
 * ProductOrderDto.java
 *
 * created at 2019-11-19 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


public class ProductOrderFormDto
{
    private Integer productId;
    private Integer orderId;
    private Integer quantity;


    public Integer getProductId()
    {
        return productId;
    }


    public Integer getOrderId()
    {
        return orderId;
    }


    public Integer getQuantity()
    {
        return quantity;
    }


    public void setProductId(Integer productId)
    {
        this.productId = productId;
    }


    public void setOrderId(Integer orderId)
    {
        this.orderId = orderId;
    }


    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

}
