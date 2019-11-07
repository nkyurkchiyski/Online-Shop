package com.example.shop.base.dao.impl;


import com.example.shop.base.dao.CategoryDao;
import com.example.shop.base.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;


public class CategoryDaoImpl implements CategoryDao
{

    @PersistenceContext(unitName = "online-shop")
    EntityManager entityManager;


    @Override
    public Category findByName(String categoryName)
    {
        this.entityManager.getTransaction().begin();
        final Category category = this.entityManager.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)//
                                                    .setParameter("name", categoryName)
                                                    .getSingleResult();
        this.entityManager.getTransaction().commit();
        return category;
    }


    @Override
    public Category save(Category entity)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }


    @Override
    public void update(Category entity)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
    }


    @Override
    public void delete(Category entity)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(entity);
        this.entityManager.getTransaction().commit();
    }


    @Override
    public List<Category> findAll()
    {
        this.entityManager.getTransaction().begin();
        final List<Category> categories = this.entityManager.createQuery("SELECT c FROM Category c", Category.class)//
                                                            .getResultList();
        this.entityManager.getTransaction().commit();
        return categories;
    }


    @Override
    public Category findById(Integer id)
    {
        this.entityManager.getTransaction().begin();
        final Category category = this.entityManager.find(Category.class, id);
        this.entityManager.getTransaction().commit();
        return category;
    }


    @Override
    public Integer size()
    {
        this.entityManager.getTransaction().begin();
        final Integer size = this.entityManager.createQuery("SELECT count(c) FROM Category c", Integer.class)//
                                               .getSingleResult();
        this.entityManager.getTransaction().commit();
        return size;
    }
}
