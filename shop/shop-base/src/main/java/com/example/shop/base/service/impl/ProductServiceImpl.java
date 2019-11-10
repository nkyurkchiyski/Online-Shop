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
    public Product create(Product entity) {
        final Product product = this.productDao.save(entity);
        return product;
    }


    @Override
    public Product getById(Integer id) {
        final Product product = this.productDao.findById(id);
        return product;
    }


    @Override
    public List<Product> getAll() {
        final List<Product> products = this.productDao.findAll();
        return products;
    }


    @Override
    public void update(Product entity) {
        this.productDao.update(entity);
    }


    @Override
    public void remove(Product entity) {
        this.productDao.delete(entity);
    }


    @Override
    public Product getByName(String name) {
        final Product product = this.productDao.findByName(name);
        return product;
    }


    @Override
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

}
