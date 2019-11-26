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

import com.example.shop.base.constants.ErrorMessage;
import com.example.shop.base.dao.CategoryDao;
import com.example.shop.base.dao.ProductDao;
import com.example.shop.base.dto.ProductFormDto;
import com.example.shop.base.dto.SearchDto;
import com.example.shop.base.model.Category;
import com.example.shop.base.model.Product;
import com.example.shop.base.service.ProductService;
import com.example.shop.base.util.StringUtil;

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

        if (product == null)
        {
            return null;
        }

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

        if (product == null)
        {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_DOES_NOT_EXIST);
        }

        this.updateProductInformation(dto, product);
        this.productDao.update(product);
        return this.mapper.map(product, type);
    }


    @Override
    public void remove(Integer id)
    {
        final Product product = this.getById(id, Product.class);

        if (product == null)
        {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_DOES_NOT_EXIST);
        }

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
        if (StringUtil.isNullOrEmpty(dto.getDescription()) || StringUtil.isNullOrEmpty(dto.getName()) //
            || dto.getPrice() == null || dto.getQuantity() == null || dto.getCategoryIds() == null //
            || StringUtil.isNullOrEmpty(dto.getImageUrl()))
        {
            throw new IllegalArgumentException(String.format(ErrorMessage.MANDATORY_FIELDS, "All"));
        }

        if (dto.getPrice().compareTo(new BigDecimal(0)) != 1)
        {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_INVALID_PRICE);
        }

        if (dto.getQuantity() == 0)
        {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_INVALID_QUANTITY);
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
            throw new IllegalArgumentException(ErrorMessage.SEARCH_MIN_MAX_PRICE);
        }

    }


    @Override
    public void decreaseQuantity(Integer productId, Integer quantity)
    {
        final Product product = this.productDao.findById(productId);

        if (product == null)
        {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_DOES_NOT_EXIST);
        }

        Integer newQuantity = product.getQuantity() - quantity;

        if (newQuantity < 0)
        {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_DESCREASE_QUANTITY);
        }

        product.setQuantity(newQuantity);
        this.productDao.update(product);

    }
}
