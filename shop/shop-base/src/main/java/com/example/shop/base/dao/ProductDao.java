/*
 * ProductDao.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao;


import java.util.List;

import com.example.shop.base.dto.SearchDto;
import com.example.shop.base.model.Product;


public interface ProductDao extends GenericDao<Product, Integer>
{
    List<Product> findAllByFilters(SearchDto dto);


    List<Product> findAllByFiltersPaginated(SearchDto dto, int firstResult, int maxResults);


    Long sizeAllByFilters(SearchDto dto);

}
