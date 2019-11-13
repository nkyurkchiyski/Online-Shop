/*
 * OrderServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;

import com.example.shop.base.dao.OrderDao;
import com.example.shop.base.dto.OrderDto;
import com.example.shop.base.model.Order;
import com.example.shop.base.service.OrderService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;

import javax.inject.Inject;


@Service(classes = OrderService.class)
@Bean(id = "orderService")
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public <T> T create(OrderDto dto, Class<T> type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T getById(Integer id, Class<T> type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<T> getAll(Class<T> type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T update(OrderDto dto, Class<T> type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(Integer id) {

    }
}
