/*
 * ProductOrderId.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.model.key;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ProductOrderId implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "cProductOrderProductId")
    private Long productId;

    @Column(name = "cProductOrderOrderId")
    private Long orderId;


    public ProductOrderId()
    {
    }


    public Long getProductId()
    {
        return productId;
    }


    public Long getOrderId()
    {
        return orderId;
    }


    public void setProductId(Long productId)
    {
        this.productId = productId;
    }


    public void setOrderId(Long orderId)
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



