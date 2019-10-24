/*
 * ProductOrder.java
 *
 * created at 2019-10-23 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.example.model.key.ProductOrderId;


@Entity
@Table(name = "t_ProductOrders")
public class ProductOrder implements Serializable
{
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProductOrderId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "cProductOrderProductId", foreignKey = @ForeignKey(name = "FK_ProductOrders_Products"))
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "cProductOrderOrderId", foreignKey = @ForeignKey(name = "FK_ProductOrders_Orders"))
    private Order order;

    @Column(name = "cProductOrderQuantity")
    private int quantity;


    public ProductOrder()
    {
    }


    public ProductOrderId getId()
    {
        return id;
    }


    public Product getProduct()
    {
        return product;
    }


    public Order getOrder()
    {
        return order;
    }


    public int getQuantity()
    {
        return quantity;
    }


    public void setId(ProductOrderId id)
    {
        this.id = id;
    }


    public void setProduct(Product product)
    {
        this.product = product;
    }


    public void setOrder(Order order)
    {
        this.order = order;
    }


    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

}
