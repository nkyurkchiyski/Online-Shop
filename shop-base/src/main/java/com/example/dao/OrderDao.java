/*
 * OrderDao.java
 *
 * created at 2019-10-23 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.dao;


import com.example.model.Order;


public interface OrderDao extends GenericDao<Order, Long>
{
    Order findByName(String categoryName);
}
