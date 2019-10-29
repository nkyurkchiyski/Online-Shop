/*
 * OrderServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;

import com.example.shop.base.dao.OrderDao;
import com.example.shop.base.model.Order;
import com.example.shop.base.service.OrderService;


public class OrderServiceImpl implements OrderService
{
    private OrderDao orderDao;


    @Override
    public Order create(Order entity)
    {
        final Order order = this.orderDao.save(entity);
        return order;
    }


    @Override
    public Order getById(Long id)
    {
        final Order order = this.orderDao.findById(id);
        return order;
    }


    @Override
    public List<Order> getAll()
    {
        final List<Order> orders = this.orderDao.findAll();
        return orders;
    }


    @Override
    public void update(Order entity)
    {
        this.orderDao.update(entity);
    }


    @Override
    public void remove(Order entity)
    {
        this.orderDao.delete(entity);
    }


    @Override
    public void setOrderDao(OrderDao orderDao)
    {
        this.orderDao = orderDao;
    }

}
