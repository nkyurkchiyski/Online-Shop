package com.example.shop.base.dao.impl;


import com.example.shop.base.dao.OrderDao;
import com.example.shop.base.model.Order;
import com.example.shop.base.model.OrderStatus;
import com.example.shop.base.model.User;

import org.apache.aries.blueprint.annotation.bean.Bean;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;


@Transactional
@Bean(id = "orderDao")
public class OrderDaoImpl implements OrderDao
{
    @PersistenceContext(unitName = "online-shop")
    EntityManager em;


    @Override
    public Order save(Order entity)
    {
        this.em.getTransaction().begin();
        this.em.persist(entity);
        this.em.getTransaction().commit();
        return entity;
    }


    @Override
    public Order update(Order entity)
    {
        this.em.getTransaction().begin();
        this.em.merge(entity);
        this.em.getTransaction().commit();
        return entity;
    }


    @Override
    public void delete(Order entity)
    {
        this.em.getTransaction().begin();
        this.em.remove(entity);
        this.em.getTransaction().commit();
    }


    @Override
    public List<Order> findAll()
    {
        final List<Order> orders = this.em.createQuery("SELECT o FROM Order o", Order.class)//
                                          .getResultList();
        return orders;
    }


    @Override
    public Order findById(Integer id)
    {
        Order order;
        try
        {
            order = this.em.createQuery("SELECT o FROM Order o " //
                                        + "LEFT JOIN FETCH o.products " //
                                        + "WHERE o.id = :id",
                                        Order.class)//
                           .setParameter("id", id)
                           .getSingleResult();
        }
        catch (NoResultException e)
        {
            order = null;
        }
        return order;
    }


    @Override
    public Integer size()
    {
        return this.em.createQuery("SELECT count(o) FROM Order o", Integer.class)//
                      .getSingleResult();

    }


    @Override
    public Order findCartOfUser(User user)
    {
        Order order;
        try
        {
            order = this.em.createQuery("SELECT o FROM Order o " //
                                        + "LEFT JOIN FETCH o.user u " //
                                        + "LEFT JOIN FETCH o.products p " //
                                        + "LEFT JOIN FETCH p.product " //
                                        + "WHERE u.id = :id AND o.status = :status",
                                        Order.class)//
                           .setParameter("id", user.getId())
                           .setParameter("status", OrderStatus.CART)
                           .getSingleResult();
        }
        catch (NoResultException e)
        {
            order = null;
        }
        return order;
    }
}
