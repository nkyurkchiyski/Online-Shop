/*
 * ProductDao.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao;


import java.math.BigDecimal;
import java.util.List;

import com.example.shop.base.model.Product;


public interface ProductDao extends GenericDao<Product, Integer>
{
    Product findByName(String productName);


    List<Product> findAllByFilters(String[] searchTerms, String categoryIds, BigDecimal minPrice, BigDecimal maxPrice);


    List<Product> findAllByFiltersPaginated(String[] searchTerms, String categoryIds, BigDecimal minPrice, BigDecimal maxPrice, int firstResult, int maxResults);
}
