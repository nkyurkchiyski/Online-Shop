/*
 * Order.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.model;


import java.io.Serializable;
import java.time.LocalDate;
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
    private Long id;

    @Column(name = "cOrderOrderedOn")
    private LocalDate orderedOn;

    @Column(name = "cOrderDeliveredOn")
    private LocalDate deliveredOn;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "cOrderStatus")
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "cOrderUserId", foreignKey = @ForeignKey(name = "FK_Orders_Users"))
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductOrder> products;


    public Order()
    {
    }


    public Long getId()
    {
        return id;
    }


    public LocalDate getOrderedOn()
    {
        return orderedOn;
    }


    public LocalDate getDeliveredOn()
    {
        return deliveredOn;
    }


    public User getUser()
    {
        return user;
    }


    public OrderStatus getStatus()
    {
        return status;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public void setOrderedOn(LocalDate orderedOn)
    {
        this.orderedOn = orderedOn;
    }


    public void setDeliveredOn(LocalDate deliveredOn)
    {
        this.deliveredOn = deliveredOn;
    }


    public void setUser(User user)
    {
        this.user = user;
    }


    public void setStatus(OrderStatus status)
    {
        this.status = status;
    }

}



