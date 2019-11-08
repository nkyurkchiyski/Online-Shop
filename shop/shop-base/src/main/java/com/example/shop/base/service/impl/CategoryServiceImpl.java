/*
 * CategoryServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;

import javax.inject.Inject;

import org.ops4j.pax.cdi.api.*;

import com.example.shop.base.dao.CategoryDao;
import com.example.shop.base.model.Category;
import com.example.shop.base.service.CategoryService;

@Service @Component
@Immediate
public class CategoryServiceImpl implements CategoryService
{
    @Inject
    private CategoryDao categoryDao;


    @Override
    public Category create(Category entity)
    {
        return this.categoryDao.save(entity);
    }


    @Override
    public Category getById(Integer id)
    {
        return this.categoryDao.findById(id);
    }


    @Override
    public List<Category> getAll()
    {
        return this.categoryDao.findAll();
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
        return this.categoryDao.findByName(name);
    }


    @Override
    public void setCategoryDao(CategoryDao categoryDao)
    {
        this.categoryDao = categoryDao;
    }

}
