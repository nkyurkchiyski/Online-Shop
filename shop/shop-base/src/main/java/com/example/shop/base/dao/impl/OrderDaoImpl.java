package com.example.shop.base.dao.impl;


import com.example.shop.base.constants.ErrorMessage;
import com.example.shop.base.dao.OrderDao;
import com.example.shop.base.exception.NoSuchEntityException;
import com.example.shop.base.exception.NonUniqueEntityException;
import com.example.shop.base.model.Order;
import com.example.shop.base.model.OrderStatus;

import org.apache.aries.blueprint.annotation.bean.Bean;

import javax.persistence.EntityManager;
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
    public void update(Order entity)
    {
        this.em.getTransaction().begin();
        this.em.merge(entity);
        this.em.getTransaction().commit();
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
        return this.em.createQuery("SELECT o FROM Order o", Order.class)//
                      .getResultList();
    }


    @Override
    public Order findById(Integer id) throws NoSuchEntityException, NonUniqueEntityException
    {
        final List<Order> resultList = this.em.createQuery("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.user u LEFT JOIN FETCH o.products p LEFT JOIN FETCH p.product WHERE o.id = :id", Order.class)//
                                              .setParameter("id", id)
                                              .getResultList();

        if (resultList.isEmpty())
        {
            throw new NoSuchEntityException(ErrorMessage.ORDER_DOES_NOT_EXIST);
        }

        if (resultList.size() > 1)
        {
            throw new NonUniqueEntityException(ErrorMessage.UNIQUE_RESULT_NOT_FOUND);
        }
        return resultList.get(0);
    }


    @Override
    public Long size()
    {
        return this.em.createQuery("SELECT count(o) FROM Order o", Long.class)//
                      .getSingleResult();
    }


    @Override
    public Order findCartByUserId(Integer userId) throws NoSuchEntityException, NonUniqueEntityException
    {
        final List<Order> resultList = this.em.createQuery("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.user u LEFT JOIN FETCH o.products p LEFT JOIN FETCH p.product WHERE u.id = :id AND o.status = :status", Order.class)//
                                              .setParameter("id", userId)
                                              .setParameter("status", OrderStatus.CART)
                                              .getResultList();

        if (resultList.isEmpty())
        {
            throw new NoSuchEntityException(ErrorMessage.CART_DOES_NOT_EXIST);
        }

        if (resultList.size() > 1)
        {
            throw new NonUniqueEntityException(ErrorMessage.UNIQUE_RESULT_NOT_FOUND);
        }

        return resultList.get(0);
    }


    @Override
    public boolean existsCartWithUserId(Integer userId)
    {
        final List<Order> resultList = this.em.createQuery("SELECT o FROM Order o LEFT JOIN FETCH o.user u WHERE u.id = :id AND o.status = :status", Order.class)//
                                              .setParameter("id", userId)
                                              .setParameter("status", OrderStatus.CART)
                                              .getResultList();
        return !resultList.isEmpty();
    }


    @Override
    public List<Order> findAllPlacedByUserId(Integer userId)
    {
        return this.em.createQuery("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.user u LEFT JOIN FETCH o.products p WHERE u.id = :id AND o.status != :status", Order.class)//
                      .setParameter("id", userId)
                      .setParameter("status", OrderStatus.CART)
                      .getResultList();
    }


    @Override
    public List<Order> findAllPlaced()
    {
        return this.em.createQuery("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.user u LEFT JOIN FETCH o.products p WHERE o.status != :status", Order.class)//
                      .setParameter("status", OrderStatus.CART)
                      .getResultList();
    }


    @Override
    public List<Order> findAllPaginated(int firstResult, int maxResults)
    {
        return this.em.createQuery("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.user u LEFT JOIN FETCH o.products p", Order.class)//
                      .setFirstResult(firstResult)
                      .setMaxResults(maxResults)
                      .getResultList();
    }


    @Override
    public List<Order> findAllPlacedPaginated(int firstResult, int maxResults)
    {
        return this.em.createQuery("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.user u LEFT JOIN FETCH o.products p WHERE o.status != :status", Order.class)//
                      .setParameter("status", OrderStatus.CART)
                      .setFirstResult(firstResult)
                      .setMaxResults(maxResults)
                      .getResultList();
    }


    @Override
    public Long sizeAllPlaced()
    {
        return this.em.createQuery("SELECT DISTINCT count(o) FROM Order o WHERE o.status != :status", Long.class)//
                      .setParameter("status", OrderStatus.CART)
                      .getSingleResult();
    }


    @Override
    public List<Order> findAllPlacedByUserIdPaginated(Integer userId, int firstResult, int maxResults)
    {
        return this.em.createQuery("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.user u LEFT JOIN FETCH o.products p WHERE u.id = :id AND o.status != :status", Order.class)//
                      .setParameter("id", userId)
                      .setParameter("status", OrderStatus.CART)
                      .setFirstResult(firstResult)
                      .setMaxResults(maxResults)
                      .getResultList();
    }


    @Override
    public Long sizeAllPlacedByUserId(Integer userId)
    {
        return this.em.createQuery("SELECT DISTINCT count(o) FROM Order o JOIN o.user u WHERE u.id = :id AND o.status != :status", Long.class)//
                      .setParameter("id", userId)
                      .setParameter("status", OrderStatus.CART)
                      .getSingleResult();
    }

}
