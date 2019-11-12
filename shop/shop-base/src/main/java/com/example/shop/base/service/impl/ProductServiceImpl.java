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
    public ProductDto create(ProductDto dto)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProductDto getById(Integer id)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProductDto> getAll()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProductDto update(ProductDto dto)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(ProductDto dto)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public ProductDto getByName(String name)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setProductDao(ProductDao productDao)
    {
        // TODO Auto-generated method stub

    }

}
