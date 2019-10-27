package com.example.shop.base.dao.impl;

import com.example.shop.base.dao.OrderDao;
import com.example.shop.base.model.Order;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private final EntityManager entityManager;

    public OrderDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Order save(Order entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public void update(Order entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
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
        List<Order> orders = this.entityManager
                .createQuery("SELECT o FROM t_Orders o", Order.class)
                .getResultList();
        this.entityManager.getTransaction().commit();
        return orders;
    }

    @Override
    public Order findById(Long id) {
        this.entityManager.getTransaction().begin();
        Order order = this.entityManager.find(Order.class, id);
        this.entityManager.getTransaction().commit();
        return order;
    }

    @Override
    public long size() {
        this.entityManager.getTransaction().begin();
        Long size = this.entityManager
                .createQuery("SELECT count(o) FROM t_Orders o", Long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return size;
    }
}
