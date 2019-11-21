/*
 * CartServiceImpl.java
 *
 * created at 2019-11-21 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;

import javax.inject.Inject;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import com.example.shop.base.dto.ProductOrderFormDto;
import com.example.shop.base.service.CartService;


@Service(classes = CartService.class)
@Bean(id = "cartService")
public class CartServiceImpl implements CartService
{

    @Override
    public void addProduct(Integer userId, ProductOrderFormDto dto)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeProduct(Integer userId, Integer productId)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public <T> T getByUserId(Integer userId, Class<T> type)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Integer userId, List<ProductOrderFormDto> productOrderDtos)
    {
        // TODO Auto-generated method stub

    }


}
