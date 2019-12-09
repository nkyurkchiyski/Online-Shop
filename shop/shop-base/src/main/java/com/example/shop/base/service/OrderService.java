/*
 * OrderService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import java.io.IOException;
import java.util.List;

import com.example.shop.base.dto.OrderDto;
import com.example.shop.base.dto.ProductOrderFormDto;


public interface OrderService
{
    <T> List<T> getAllByUserId(Integer userId, Class<T> type);


    <T> T getById(Integer id, Class<T> type);


    <T> List<T> getAll(Class<T> type);


    void placeOrder(Integer userId, List<ProductOrderFormDto> productOrderDtos);


    void approveOrder(Integer orderId);


    <T> T create(OrderDto dto, Class<T> type);


    byte[] generateInvoice(Integer orderId) throws IOException, IllegalArgumentException, IllegalAccessException;


    int getAllPages();


    <T> List<T> getAllPaginated(int pageNumber, Class<T> type);


    int getAllPlacedPages();


    <T> List<T> getAllPlacedPaginated(int pageNumber, Class<T> type);


    int getAllByUserIdPages(Integer userId);


    <T> List<T> getAllByUserIdPaginated(Integer userId, int pageNumber, Class<T> type);

}
