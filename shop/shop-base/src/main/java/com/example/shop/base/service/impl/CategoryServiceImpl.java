/*
 * CategoryServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.example.shop.base.constants.ErrorMessage;
import com.example.shop.base.dao.CategoryDao;
import com.example.shop.base.dto.CategoryDto;
import com.example.shop.base.service.CategoryService;
import com.example.shop.base.util.StringUtil;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.modelmapper.ModelMapper;

import com.example.shop.base.model.Category;


@Service(classes = CategoryService.class)
@Bean(id = "categoryService")
public class CategoryServiceImpl implements CategoryService
{
    private static final int PAGE_SIZE = 12;

    @Inject
    private CategoryDao categoryDao;

    private ModelMapper mapper;


    public CategoryServiceImpl()
    {
        this.mapper = new ModelMapper();
    }


    @Override
    public <T> T create(CategoryDto dto, Class<T> type)
    {
        this.validateCategoryDto(dto);
        final Category category = this.mapper.map(dto, Category.class);
        this.categoryDao.save(category);
        return this.mapper.map(category, type);
    }


    @Override
    public <T> T getById(Integer id, Class<T> type)
    {

        final Category category = this.categoryDao.findById(id);
        return this.mapper.map(category, type);

    }


    @Override
    public <T> List<T> getAll(Class<T> type)
    {
        final List<Category> categories = this.categoryDao.findAll();
        return categories.stream()
                         .map(source -> this.mapper.map(source, type))//
                         .collect(Collectors.toList());
    }


    @Override
    public <T> T update(CategoryDto dto, Class<T> type)
    {
        final Category category = this.getById(dto.getId(), Category.class);

        this.validateCategoryDto(dto);
        category.setName(dto.getName());

        this.categoryDao.update(category);
        return this.mapper.map(category, type);
    }


    @Override
    public void remove(Integer id)
    {
        final Category category = this.getById(id, Category.class);
        this.categoryDao.delete(category);
    }


    @Override
    public <T> T getByName(String name, Class<T> type)
    {
        final Category category = this.categoryDao.findByName(name);
        return this.mapper.map(category, type);
    }


    @Override
    public <T> List<T> getAllPaginated(int pageNumber, Class<T> type)
    {
        final int size = Math.toIntExact(this.categoryDao.size());
        int firstResult = pageNumber * PAGE_SIZE;
        firstResult = firstResult > size || firstResult < 0 ? 0 : firstResult;

        final List<Category> categories = this.categoryDao.findAllPaginated(firstResult, PAGE_SIZE);
        return categories.stream()
                         .map(source -> this.mapper.map(source, type))//
                         .collect(Collectors.toList());
    }


    @Override
    public int getAllPages()
    {
        final int size = Math.toIntExact(this.categoryDao.size());
        final int remainder = size % PAGE_SIZE;
        final int pages = size / PAGE_SIZE;
        return remainder == 0 ? pages : pages + 1;
    }


    private void validateCategoryDto(CategoryDto dto)
    {
        if (StringUtil.isNullOrEmpty(dto.getName()))
        {
            throw new IllegalArgumentException(String.format(ErrorMessage.MANDATORY_FIELDS, "All"));
        }

        if (this.categoryDao.existsWithName(dto.getName()))
        {
            throw new IllegalArgumentException(ErrorMessage.CATEGORY_ALREADY_EXISTS);
        }
    }
}
