/*
 * CartService.java
 *
 * created at 2019-11-21 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import java.util.List;

import com.example.shop.base.dto.ProductOrderFormDto;


public interface CartService
{

    void addProduct(Integer userId, ProductOrderFormDto dto);


    void removeProduct(Integer userId, Integer productId);


    void update(Integer userId, List<ProductOrderFormDto> productOrderDtos);


    <T> T getOrCreate(Integer userId, Class<T> type);

}
