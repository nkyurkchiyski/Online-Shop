/*
 * ProductService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import com.example.shop.base.dto.ProductFormDto;
import com.example.shop.base.dto.SearchFormDto;

import java.util.List;


public interface ProductService extends GenericService<ProductFormDto, Integer>
{
    <T> List<T> getAllBySearch(SearchFormDto dto, Class<T> type);


    void decreaseQuantity(Integer productId, Integer quantity);


    int getAllPages();


    <T> List<T> getAllPaginated(int pageNumber, Class<T> type);


    int getAllBySearchPages(SearchFormDto dto);


    <T> List<T> getAllBySearchPaginated(int pageNumber, SearchFormDto dto, Class<T> type);

}
