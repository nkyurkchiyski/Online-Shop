/*
 * ProductService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import com.example.shop.base.dao.ProductDao;
import com.example.shop.base.model.Product;

import javax.jws.WebService;


@WebService
public interface ProductService extends GenericService<Product, Integer>
{
    Product getByName(String name);


    void setProductDao(ProductDao productDao);
}
