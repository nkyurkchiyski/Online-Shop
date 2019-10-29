/*
 * OrderService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import com.example.shop.base.dao.OrderDao;
import com.example.shop.base.model.Order;


public interface OrderService extends GenericService<Order, Long>
{
    void setOrderDao(OrderDao orderDao);
}
