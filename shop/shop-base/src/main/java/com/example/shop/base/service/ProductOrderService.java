/*
 * ProductOrderService.java
 *
 * created at 2019-11-21 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import java.util.List;

import com.example.shop.base.dto.ProductOrderFormDto;


public interface ProductOrderService
{
    <T> T getByProductIdAndOrderId(Integer productId, Integer orderId, Class<T> type);


    <T> T create(Integer quantity, Integer productId, Integer orderId, Class<T> type);


    <T> T update(Integer quantity, Integer productId, Integer orderId, Class<T> type);


    <T> List<T> updateAll(Integer orderId, List<ProductOrderFormDto> productOrderDtos, Class<T> type);


    void remove(Integer productId, Integer orderId);


    boolean exists(Integer productId, Integer orderId);
}
