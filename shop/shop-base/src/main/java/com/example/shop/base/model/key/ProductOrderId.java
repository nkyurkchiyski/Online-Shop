/*
 * ProductOrderId.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.model.key;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ProductOrderId implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "cProductOrderProductId")
    private Integer productId;

    @Column(name = "cProductOrderOrderId")
    private Integer orderId;


    public ProductOrderId()
    {
    }


    public Integer getProductId()
    {
        return productId;
    }


    public Integer getOrderId()
    {
        return orderId;
    }


    public void setProductId(Integer productId)
    {
        this.productId = productId;
    }


    public void setOrderId(Integer orderId)
    {
        this.orderId = orderId;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ProductOrderId productOrderId = (ProductOrderId)o;
        return Objects.equals(this.orderId, productOrderId.orderId) //
               && Objects.equals(this.productId, productOrderId.productId);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(this.productId, this.orderId);
    }

}
