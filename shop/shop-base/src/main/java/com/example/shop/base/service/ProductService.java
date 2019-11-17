/*
 * ProductService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;

import com.example.shop.base.dto.ProductFormDto;

import java.util.List;


public interface ProductService extends GenericService<ProductFormDto, Integer> {
    <T> List<T> getAllByName(String name, Class<T> type);
}
