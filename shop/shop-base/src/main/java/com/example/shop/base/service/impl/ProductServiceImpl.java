/*
 * ProductServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.shop.base.dao.CategoryDao;
import com.example.shop.base.dao.ProductDao;
import com.example.shop.base.dto.ProductFormDto;
import com.example.shop.base.dto.SearchDto;
import com.example.shop.base.model.Category;
import com.example.shop.base.model.Product;
import com.example.shop.base.service.ProductService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;


@Service(classes = ProductService.class)
@Bean(id = "productService")
public class ProductServiceImpl implements ProductService
{
    @Inject
    private ProductDao productDao;

    @Inject
    private CategoryDao categoryDao;

    private ModelMapper mapper;


    public ProductServiceImpl()
    {
        this.mapper = new ModelMapper();
    }


    @Override
    public <T> T create(ProductFormDto dto, Class<T> type)
    {
        this.validateProductFormDto(dto);
        final Product product = this.mapper.map(dto, Product.class);
        final Set<Category> categories = this.getCategories(dto.getCategoryIds());
        product.setCategories(categories);
        this.productDao.save(product);
        return this.mapper.map(product, type);
    }


    private Set<Category> getCategories(Integer[] categoryIds)
    {
        final Set<Category> categories = new HashSet<>();
        for (int id : categoryIds)
        {
            final Category category = this.categoryDao.findById(id);
            categories.add(category);
        }
        return categories;
    }


    @Override
    public <T> T getById(Integer id, Class<T> type)
    {
        final Product product = this.productDao.findById(id);
        return this.mapper.map(product, type);
    }


    @Override
    public <T> List<T> getAll(Class<T> type)
    {
        final List<Product> products = this.productDao.findAll();
        return products.stream()
                       .map(source -> this.mapper.map(source, type))//
                       .collect(Collectors.toList());
    }


    @Override
    public <T> T update(ProductFormDto dto, Class<T> type)
    {
        this.validateProductFormDto(dto);
        final Product product = this.getById(dto.getId(), Product.class);
        this.updateProductInformation(dto, product);
        this.productDao.update(product);
        return this.mapper.map(product, type);
    }


    @Override
    public void remove(Integer id)
    {
        final Product product = this.getById(id, Product.class);
        product.setActive(false);
        this.productDao.update(product);
    }


    @Override
    public <T> List<T> getAllBySearch(SearchDto dto, Class<T> type)
    {
        this.validateSearchDto(dto);
        final String categoryIds = convertIdsToString(dto.getCategoryIds());
        final String[] searchTerms = dto.getTerm() != null ? dto.getTerm().split(" ") : null;

        final List<Product> products = this.productDao.findAllByFilters(searchTerms, categoryIds, dto.getMinPrice(), dto.getMaxPrice());
        return products.stream()
                       .map(source -> this.mapper.map(source, type))//
                       .collect(Collectors.toList());
    }


    private String convertIdsToString(Integer[] ids)
    {
        if (ids == null)
        {
            return null;
        }

        final String idsTemp = Arrays.toString(ids);
        return idsTemp.substring(1, idsTemp.length() - 1);
    }


    private void updateProductInformation(ProductFormDto dto, Product product)
    {
        if (product != null)
        {
            final Set<Category> categories = this.getCategories(dto.getCategoryIds());
            product.setCategories(categories);
            product.setDescription(dto.getDescription());
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setImageUrl(dto.getImageUrl());
            product.setQuantity(dto.getQuantity());
        }
    }


    private void validateProductFormDto(ProductFormDto dto)
    {
        if (dto.getDescription() == null || dto.getName() == null //
            || dto.getPrice() == null || dto.getQuantity() == null //
            || dto.getImageUrl() == null || dto.getCategoryIds() == null)
        {
            throw new IllegalArgumentException("All fields are mandatory!");
        }

        if (dto.getPrice().compareTo(new BigDecimal(0)) != 1)
        {
            throw new IllegalArgumentException("The product price should be greater than 0!");
        }

        if (dto.getQuantity() == 0)
        {
            throw new IllegalArgumentException("The product quantity should be greater than 0!");
        }

        if (dto.getName().isEmpty())
        {
            throw new IllegalArgumentException("The product name cannot be empty!");
        }

        if (dto.getDescription().isEmpty())
        {
            throw new IllegalArgumentException("The product description cannot be empty!");
        }

        if (dto.getImageUrl().isEmpty())
        {
            throw new IllegalArgumentException("The product image url cannot be empty!");
        }

    }


    private void validateSearchDto(SearchDto dto)
    {
        if (dto.getMinPrice() == null)
        {
            dto.setMinPrice(new BigDecimal(0));
        }

        if (dto.getMaxPrice() == null)
        {
            dto.setMaxPrice(new BigDecimal(Integer.MAX_VALUE));
        }

        if (dto.getMinPrice() != null && dto.getMaxPrice() != null //
            && dto.getMinPrice().compareTo(dto.getMaxPrice()) == 1)
        {
            throw new IllegalArgumentException("The minimum price cannot be bigger than max price!");
        }

    }


    @Override
    public void decreaseQuantity(Integer productId, Integer quantity)
    {
        final Product product = this.productDao.findById(productId);

        if (product != null)
        {
            Integer newQuantity = product.getQuantity() - quantity;

            if (newQuantity < 0)
            {
                throw new IllegalArgumentException("Quantity exceeds present product units!");
            }

            product.setQuantity(newQuantity);
            this.productDao.update(product);
        }

    }
}
