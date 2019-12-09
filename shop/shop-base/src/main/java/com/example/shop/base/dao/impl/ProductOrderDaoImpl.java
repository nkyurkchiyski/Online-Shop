/*
 * ProductOrderDaoImpl.java
 *
 * created at 2019-11-22 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.aries.blueprint.annotation.bean.Bean;

import com.example.shop.base.constants.ErrorMessage;
import com.example.shop.base.dao.ProductOrderDao;
import com.example.shop.base.exception.NoSuchEntityException;
import com.example.shop.base.exception.NonUniqueEntityException;
import com.example.shop.base.model.ProductOrder;


@Transactional
@Bean(id = "productOrderDao")
public class ProductOrderDaoImpl implements ProductOrderDao
{
    @PersistenceContext(unitName = "online-shop")
    EntityManager em;


    @Override
    public ProductOrder save(ProductOrder entity)
    {
        this.em.getTransaction().begin();
        this.em.persist(entity);
        this.em.getTransaction().commit();
        return entity;
    }


    @Override
    public ProductOrder update(ProductOrder entity)
    {
        this.em.getTransaction().begin();
        this.em.merge(entity);
        this.em.getTransaction().commit();
        return entity;
    }


    @Override
    public void delete(ProductOrder entity)
    {
        this.em.getTransaction().begin();
        this.em.remove(this.em.merge(entity));
        this.em.getTransaction().commit();
    }


    @Override
    public ProductOrder findByProductIdAndOrderId(Integer productId, Integer orderId) throws NoSuchEntityException, NonUniqueEntityException
    {
        final List<ProductOrder> resultList = this.em.createQuery("SELECT DISTINCT po FROM ProductOrder po LEFT JOIN FETCH po.order o LEFT JOIN FETCH po.product p WHERE p.id = :productId AND o.id = :orderId", ProductOrder.class)//
                                                     .setParameter("productId", productId)
                                                     .setParameter("orderId", orderId)
                                                     .getResultList();

        if (resultList.isEmpty())
        {
            throw new NoSuchEntityException(ErrorMessage.PRODUCT_ORDER_DOES_NOT_EXIST);
        }

        if (resultList.size() > 1)
        {
            throw new NonUniqueEntityException(ErrorMessage.UNIQUE_RESULT_NOT_FOUND);
        }

        return resultList.get(0);
    }


    @Override
    public boolean existsWithProductIdAndOrderId(Integer productId, Integer orderId)
    {
        final List<ProductOrder> resultList = this.em.createQuery("SELECT po FROM ProductOrder po LEFT JOIN FETCH po.order o LEFT JOIN FETCH po.product p WHERE p.id = :productId AND o.id = :orderId", ProductOrder.class)//
                                                     .setParameter("productId", productId)
                                                     .setParameter("orderId", orderId)
                                                     .getResultList();

        return !resultList.isEmpty();
    }

}
