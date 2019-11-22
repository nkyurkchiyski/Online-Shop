/*
 * OrderDao.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao;


import java.util.List;

import com.example.shop.base.model.Order;


public interface OrderDao extends GenericDao<Order, Integer>
{

    Order findCartOfUser(Integer userId);


    List<Order> findAllFinishedOfUser(Integer userId);
}
