/*
 * ProductServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;

import com.example.shop.base.dao.ProductDao;
import com.example.shop.base.dto.ProductDto;
import com.example.shop.base.model.Product;
import com.example.shop.base.service.ProductService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;

import javax.inject.Inject;


@Service(classes = ProductService.class)
@Bean(id = "productService")
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public <T> T create(ProductDto dto, Class<T> type)
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
    public <T> T update(ProductDto dto, Class<T> type)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public <T> T getByName(String name, Class<T> type) {
        return null;
    }
}
