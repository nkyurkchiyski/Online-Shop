/*
 * ProductService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import com.example.shop.base.dto.ProductFormDto;
import com.example.shop.base.dto.SearchDto;

import java.util.List;


public interface ProductService extends GenericService<ProductFormDto, Integer>
{
    <T> List<T> getAllBySearch(SearchDto dto, Class<T> type);


    <T> List<T> getAllBySearchPaginated(SearchDto dto, int pageNumber, Class<T> type);


    void decreaseQuantity(Integer productId, Integer quantity);
}
