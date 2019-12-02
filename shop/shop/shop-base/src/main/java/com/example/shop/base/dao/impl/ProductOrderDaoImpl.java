/*
 * ProductOrderDaoImpl.java
 *
 * created at 2019-11-22 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao.impl;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.aries.blueprint.annotation.bean.Bean;

import com.example.shop.base.dao.ProductOrderDao;
import com.example.shop.base.model.ProductOrder;


@Transactional
@Bean(id = "ProductOrderDao")
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
    public ProductOrder find(Integer productId, Integer orderId)
    {
        ProductOrder productOrder;
        try
        {
            productOrder = this.em.createQuery("SELECT po FROM ProductOrder po " //
                                               + "LEFT JOIN FETCH po.order o " //
                                               + "LEFT JOIN FETCH po.product p " //
                                               + "WHERE p.id = :productId AND o.id = :orderId",
                                               ProductOrder.class)//
                                  .setParameter("productId", productId)
                                  .setParameter("orderId", orderId)
                                  .getSingleResult();
        }
        catch (NoResultException e)
        {
            productOrder = null;
        }
        return productOrder;
    }

}
