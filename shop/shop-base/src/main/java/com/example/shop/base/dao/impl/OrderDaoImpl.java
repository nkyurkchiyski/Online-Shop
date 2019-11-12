package com.example.shop.base.dao.impl;


import com.example.shop.base.dao.OrderDao;
import com.example.shop.base.model.Order;
import org.apache.aries.blueprint.annotation.bean.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

@Transactional
@Bean(id = "orderDao")
public class OrderDaoImpl implements OrderDao {
    @PersistenceContext(unitName = "online-shop")
    EntityManager entityManager;


    @Override
    public Order save(Order entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }


    @Override
    public Order update(Order entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }


    @Override
    public void delete(Order entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(entity);
        this.entityManager.getTransaction().commit();
    }


    @Override
    public List<Order> findAll() {
        this.entityManager.getTransaction().begin();
        final List<Order> orders = this.entityManager.createQuery("SELECT o FROM t_Orders o", Order.class)//
                .getResultList();
        this.entityManager.getTransaction().commit();
        return orders;
    }


    @Override
    public Order findById(Integer id) {
        this.entityManager.getTransaction().begin();
        final Order order = this.entityManager.find(Order.class, id);
        this.entityManager.getTransaction().commit();
        return order;
    }


    @Override
    public Integer size() {
        this.entityManager.getTransaction().begin();
        final Integer size = this.entityManager.createQuery("SELECT count(o) FROM t_Orders o", Integer.class)//
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return size;
    }
}
