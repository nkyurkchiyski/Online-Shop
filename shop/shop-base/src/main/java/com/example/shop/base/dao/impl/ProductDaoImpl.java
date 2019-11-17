package com.example.shop.base.dao.impl;


import com.example.shop.base.dao.ProductDao;
import com.example.shop.base.model.Product;
import org.apache.aries.blueprint.annotation.bean.Bean;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

@Transactional
@Bean(id = "productDao")
public class ProductDaoImpl implements ProductDao {
    @PersistenceContext(unitName = "online-shop")
    EntityManager em;


    @Override
    public Product findByName(String productName) {
        Product product;
        try {
            product = this.em.createQuery("SELECT p FROM Product p WHERE name = :name", Product.class)//
                    .setParameter("name", productName)
                    .getSingleResult();
        } catch (NoResultException e) {
            product = null;
        }
        return product;
    }


    @Override
    public Product save(Product entity) {
        this.em.getTransaction().begin();
        this.em.persist(entity);
        this.em.getTransaction().commit();
        return entity;
    }


    @Override
    public Product update(Product entity) {
        this.em.getTransaction().begin();
        this.em.merge(entity);
        this.em.getTransaction().commit();
        return entity;
    }


    @Override
    public void delete(Product entity) {
        this.em.getTransaction().begin();
        this.em.remove(entity);
        this.em.getTransaction().commit();
    }


    @Override
    public List<Product> findAll() {
        final List<Product> products = this.em.createQuery("SELECT p FROM Product p " +
                "WHERE p.isActive = true", Product.class)//
                .getResultList();
        return products;
    }


    @Override
    public Product findById(Integer id) {
        Product product;
        try {
            product = this.em.createQuery("SELECT p FROM Product p " +
                    "LEFT JOIN FETCH p.categories " +
                    "LEFT JOIN FETCH p.orders " +
                    "WHERE p.id = :id", Product.class)//
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            product = null;
        }
        return product;
    }


    @Override
    public Integer size() {
        final Integer size = this.em.createQuery("SELECT count(p) FROM Product p", Integer.class)//
                .getSingleResult();
        return size;
    }
}
