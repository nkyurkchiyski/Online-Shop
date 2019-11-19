/*
 * OrderService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import java.util.List;

import com.example.shop.base.dto.OrderDto;
import com.example.shop.base.dto.ProductOrderFormDto;


public interface OrderService extends GenericService<OrderDto, Integer>
{
    public void addProductToCart(Integer userId, ProductOrderFormDto dto);


    public void removeProductFromCart(Integer userId, Integer productId);


    public <T> T getUserCart(Integer userId, Class<T> type);


    public <T> List<T> getAllByUserId(Integer userId, Class<T> type);

}
