/*
 * ProductServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.shop.base.dao.CategoryDao;
import com.example.shop.base.dao.ProductDao;
import com.example.shop.base.dto.ProductFormDto;
import com.example.shop.base.model.Category;
import com.example.shop.base.model.Product;
import com.example.shop.base.service.ProductService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;


@Service(classes = ProductService.class)
@Bean(id = "productService")
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Inject
    private CategoryDao categoryDao;

    private ModelMapper mapper;


    public ProductServiceImpl() {
        this.mapper = new ModelMapper();
    }

    @Override
    public <T> T create(ProductFormDto dto, Class<T> type) {
        //TODO add validation
        final Product product = this.mapper.map(dto, Product.class);
        final Set<Category> categories = this.getCategories(dto.getCategoryIds());
        product.setCategories(categories);
        this.productDao.save(product);
        return this.mapper.map(product, type);
    }

    private Set<Category> getCategories(Integer[] categoryIds) {
        final Set<Category> categories = new HashSet<>();
        for (int id : categoryIds) {
            final Category category = this.categoryDao.findById(id);
            categories.add(category);
        }
        return categories;
    }

    @Override
    public <T> T getById(Integer id, Class<T> type) {
        final Product product = this.productDao.findById(id);
        return this.mapper.map(product, type);
    }

    @Override
    public <T> List<T> getAll(Class<T> type) {
        final List<Product> products = this.productDao.findAll();
        return products.stream()
                .map(source -> this.mapper.map(source, type))//
                .collect(Collectors.toList());
    }

    @Override
    public <T> T update(ProductFormDto dto, Class<T> type) {
        //TODO add validation
        final Product product = this.getById(dto.getId(), Product.class);
        this.updateProductInformation(dto, product);
        this.productDao.update(product);
        return this.mapper.map(product, type);
    }

    private void updateProductInformation(ProductFormDto dto, Product product) {
        if (product != null) {
            final Set<Category> categories = this.getCategories(dto.getCategoryIds());
            product.setCategories(categories);
            product.setDescription(dto.getDescription());
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setImageUrl(dto.getImageUrl());
            product.setQuantity(dto.getQuantity());
        }
    }

    @Override
    public void remove(Integer id) {
        final Product product = this.getById(id, Product.class);
        product.setActive(false);
        this.productDao.update(product);
    }

    @Override
    public <T> List<T> getAllByName(String name, Class<T> type) {
        //TODO product DAO get all by name
        final List<Product> products = this.productDao.findAll();
        return products.stream()
                .map(source -> this.mapper.map(source, type))//
                .collect(Collectors.toList());
    }
}
