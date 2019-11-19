/*
 * OrderDao.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao;


import com.example.shop.base.model.Order;
import com.example.shop.base.model.User;


public interface OrderDao extends GenericDao<Order, Integer>
{

    Order findCartOfUser(User user);
}
