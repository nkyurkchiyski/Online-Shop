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

import com.example.shop.base.dao.CategoryDao;
import com.example.shop.base.dto.CategoryDto;
import com.example.shop.base.service.CategoryService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import com.example.shop.base.model.Category;

@Service(classes = CategoryService.class)
@Bean(id = "categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Inject
    private CategoryDao categoryDao;

    @Override
    public <T> T create(CategoryDto dto, Class<T> type)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T getById(Integer id, Class<T> type)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<T> getAll(Class<T> type)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T update(CategoryDto dto, Class<T> type)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(CategoryDto dto)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public CategoryDto getByName(String name)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCategoryDao(CategoryDao categoryDao)
    {
        // TODO Auto-generated method stub

    }



}
