package com.example.shop.base.dao.impl;


import com.example.shop.base.dao.CategoryDao;
import com.example.shop.base.model.Category;

import org.apache.aries.blueprint.annotation.bean.Bean;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;


@Transactional
@Bean(id = "categoryDao")
public class CategoryDaoImpl implements CategoryDao
{

    @PersistenceContext(unitName = "online-shop")
    EntityManager em;


    @Override
    public Category findByName(String categoryName)
    {
        Category category;
        try
        {
            category = this.em.createQuery("SELECT c FROM Category c " + "WHERE c.name = :name", Category.class)//
                              .setParameter("name", categoryName)
                              .getSingleResult();
        }
        catch (NoResultException e)
        {
            category = null;
        }
        return category;
    }


    @Override
    public Category save(Category entity)
    {
        this.em.getTransaction().begin();
        this.em.persist(entity);
        this.em.getTransaction().commit();
        return entity;
    }


    @Override
    public void update(Category entity)
    {
        this.em.getTransaction().begin();
        this.em.merge(entity);
        this.em.getTransaction().commit();
    }


    @Override
    public void delete(Category entity)
    {
        this.em.getTransaction().begin();
        this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
        this.em.getTransaction().commit();
    }


    @Override
    public List<Category> findAll()
    {
        return this.createFindAllTypedQuery()//
                   .getResultList();
    }


    @Override
    public Category findById(Integer id)
    {
        Category category;
        try
        {
            category = this.em.createQuery("SELECT c FROM Category c " //
                                           + "LEFT JOIN FETCH c.products p WHERE c.id = :id " //
                                           + "AND (p IS NULL OR p.isActive = true)",
                                           Category.class)//
                              .setParameter("id", id)
                              .getSingleResult();
        }
        catch (NoResultException e)
        {
            category = null;
        }
        return category;
    }


    @Override
    public Long size()
    {
        return this.em.createQuery("SELECT count(c) FROM Category c", Long.class)//
                      .getSingleResult();

    }


    @Override
    public List<Category> findAllPaginated(int firstResult, int maxResults)
    {
        return this.createFindAllTypedQuery()//
                   .setFirstResult(firstResult)
                   .setMaxResults(maxResults)
                   .getResultList();
    }


    private TypedQuery<Category> createFindAllTypedQuery()
    {
        return this.em.createQuery("SELECT c FROM Category c ", Category.class);
    }
}
