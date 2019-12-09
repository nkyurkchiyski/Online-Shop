/*
 * OrderDao.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao;


import java.util.List;

import com.example.shop.base.exception.NoSuchEntityException;
import com.example.shop.base.exception.NonUniqueEntityException;
import com.example.shop.base.model.Order;


public interface OrderDao extends GenericDao<Order, Integer>
{
    Order findCartByUserId(Integer userId) throws NoSuchEntityException, NonUniqueEntityException;


    List<Order> findAllPlacedByUserId(Integer userId);


    List<Order> findAllPlaced();


    boolean existsCartWithUserId(Integer userId);


    List<Order> findAllPlacedPaginated(int firstResult, int maxResults);


    Long sizeAllPlaced();


    List<Order> findAllPlacedByUserIdPaginated(Integer userId, int firstResult, int maxResults);


    Long sizeAllPlacedByUserId(Integer userId);
}
