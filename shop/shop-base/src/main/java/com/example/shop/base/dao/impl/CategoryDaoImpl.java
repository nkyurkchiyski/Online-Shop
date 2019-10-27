package com.example.shop.base.dao.impl;

import com.example.shop.base.dao.CategoryDao;
import com.example.shop.base.model.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private final EntityManager entityManager;

    public CategoryDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Category findByName(String categoryName) {
        this.entityManager.getTransaction().begin();
        Category category = this.entityManager
                .createQuery("SELECT c FROM t_Categories c WHERE c.cCategoryName = :name", Category.class)
                .setParameter("name", categoryName)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return category;
    }

    @Override
    public Category save(Category entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public void update(Category entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Category entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public List<Category> findAll() {
        this.entityManager.getTransaction().begin();
        List<Category> categories = this.entityManager
                .createQuery("SELECT c FROM t_Categories c", Category.class)
                .getResultList();
        this.entityManager.getTransaction().commit();
        return categories;
    }

    @Override
    public Category findById(Long id) {
        this.entityManager.getTransaction().begin();
        Category category = this.entityManager.find(Category.class, id);
        this.entityManager.getTransaction().commit();
        return category;
    }

    @Override
    public long size() {this.entityManager.getTransaction().begin();
        Long size = this.entityManager
                .createQuery("SELECT count(c) FROM t_Categories c", Long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return size;
    }
}
