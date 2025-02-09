/*
 * Order.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "t_Orders")
public class Order implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cOrderId")
    private Integer id;

    @Column(name = "cOrderOrderedOn")
    private LocalDateTime orderedOn;

    @Column(name = "cOrderApprovedOn")
    private LocalDateTime approvedOn;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "cOrderStatus")
    private OrderStatus status;

    @Column(name = "cOrderTotal")
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cOrderUserId", foreignKey = @ForeignKey(name = "FK_Orders_Users"))
    private User user;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE}, orphanRemoval = true)
    private Set<ProductOrder> products;


    public Order()
    {
    }


    public Integer getId()
    {
        return id;
    }


    public LocalDateTime getOrderedOn()
    {
        return orderedOn;
    }


    public LocalDateTime getApprovedOn()
    {
        return approvedOn;
    }


    public User getUser()
    {
        return user;
    }


    public OrderStatus getStatus()
    {
        return status;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public void setOrderedOn(LocalDateTime orderedOn)
    {
        this.orderedOn = orderedOn;
    }


    public void setApprovedOn(LocalDateTime approvedOn)
    {
        this.approvedOn = approvedOn;
    }


    public void setUser(User user)
    {
        this.user = user;
    }


    public void setStatus(OrderStatus status)
    {
        this.status = status;
    }


    public ProductOrder getProductOrder(Integer productId)
    {
        if (this.products == null)
        {
            return null;
        }
        return this.products.stream().filter(x -> x.getProduct().getId().equals(productId)).findFirst().orElse(null);
    }

    public Set<ProductOrder> getProducts()
    {
        return products;
    }


    public void setProducts(Set<ProductOrder> products)
    {
        this.products = products;
    }


    public BigDecimal getTotal()
    {
        return total;
    }


    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }

}
