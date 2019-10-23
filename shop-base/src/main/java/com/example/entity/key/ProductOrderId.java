/*
 * ProductOrderKey.java
 *
 * created at 2019-10-23 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.entity.key;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ProductOrderId implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "cProductOrderProductId")
    private int productId;

    @Column(name = "cProductOrderOrderId")
    private int orderId;


    public ProductOrderId()
    {
    }


    public int getProductId()
    {
        return productId;
    }


    public int getOrderId()
    {
        return orderId;
    }


    public void setProductId(int productId)
    {
        this.productId = productId;
    }


    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }


    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }


    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

}
