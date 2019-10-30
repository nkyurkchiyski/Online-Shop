package com.example.shop.base.dao.impl;


import com.example.shop.base.dao.ProductDao;
import com.example.shop.base.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;


public class ProductDaoImpl implements ProductDao
{
    @PersistenceContext(unitName = "online-shop")
    EntityManager entityManager;


    @Override
    public Product findByName(String productName)
    {
        this.entityManager.getTransaction().begin();
        Product product = this.entityManager.createQuery("SELECT p FROM Product p WHERE name = :name", Product.class).setParameter("name", productName).getSingleResult();
        this.entityManager.getTransaction().commit();
        return product;
    }


    @Override
    public Product save(Product entity)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }


    @Override
    public void update(Product entity)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
    }


    @Override
    public void delete(Product entity)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(entity);
        this.entityManager.getTransaction().commit();
    }


    @Override
    public List<Product> findAll()
    {
        this.entityManager.getTransaction().begin();
        List<Product> products = this.entityManager.createQuery("SELECT p FROM Product p JOIN FETCH p.categories", Product.class).getResultList();
        this.entityManager.getTransaction().commit();
        return products;
    }


    @Override
    public Product findById(Long id)
    {
        this.entityManager.getTransaction().begin();
        Product product = this.entityManager.find(Product.class, id);
        this.entityManager.getTransaction().commit();
        return product;
    }


    @Override
    public long size()
    {
        this.entityManager.getTransaction().begin();
        Long size = this.entityManager.createQuery("SELECT count(p) FROM Product p", Long.class).getSingleResult();
        this.entityManager.getTransaction().commit();
        return size;
    }
}
