/*
 * CategoryServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;

import com.example.shop.base.dao.CategoryDao;
import com.example.shop.base.model.Category;
import com.example.shop.base.service.CategoryService;


public class CategoryServiceImpl implements CategoryService
{
    private CategoryDao categoryDao;


    @Override
    public Category create(Category entity)
    {
        final Category category = this.categoryDao.save(entity);
        return category;
    }


    @Override
    public Category getById(Long id)
    {
        final Category category = this.categoryDao.findById(id);
        return category;
    }


    @Override
    public List<Category> getAll()
    {
        final List<Category> categories = this.categoryDao.findAll();
        return categories;
    }


    @Override
    public void update(Category entity)
    {
        this.categoryDao.update(entity);
    }


    @Override
    public void remove(Category entity)
    {
        this.categoryDao.delete(entity);
    }


    @Override
    public Category getByName(String name)
    {
        final Category category = this.categoryDao.findByName(name);
        return category;
    }


    @Override
    public void setCategoryDao(CategoryDao categoryDao)
    {
        this.categoryDao = categoryDao;
    }

}
