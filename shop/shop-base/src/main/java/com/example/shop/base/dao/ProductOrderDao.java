/*
 * ProductOrderDao.java
 *
 * created at 2019-11-22 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao;


import com.example.shop.base.exception.NoSuchEntityException;
import com.example.shop.base.exception.NonUniqueEntityException;
import com.example.shop.base.model.ProductOrder;


public interface ProductOrderDao
{

    ProductOrder save(ProductOrder entity);


    ProductOrder update(ProductOrder entity);


    void delete(ProductOrder entity);


    ProductOrder findByProductIdAndOrderId(Integer productId, Integer orderId) throws NoSuchEntityException, NonUniqueEntityException;


    boolean existsWithProductIdAndOrderId(Integer productId, Integer orderId);

}
