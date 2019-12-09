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
import org.modelmapper.ModelMapper;

import com.example.shop.base.dto.OrderDto;
import com.example.shop.base.dto.ProductOrderFormDto;
import com.example.shop.base.dto.UserDto;
import com.example.shop.base.model.Order;
import com.example.shop.base.model.OrderStatus;
import com.example.shop.base.model.ProductOrder;
import com.example.shop.base.service.CartService;
import com.example.shop.base.service.OrderService;
import com.example.shop.base.service.ProductOrderService;
import com.example.shop.base.service.UserService;


@Service(classes = CartService.class)
@Bean(id = "cartService")
public class CartServiceImpl implements CartService
{
    @Inject
    private OrderService orderService;

    @Inject
    private UserService userService;

    @Inject
    private ProductOrderService productOrderService;

    private ModelMapper mapper;


    public CartServiceImpl()
    {
        this.mapper = new ModelMapper();
    }


    @Override
    public void addProduct(Integer userId, ProductOrderFormDto dto)
    {
        final Order cart = this.getOrCreate(userId, Order.class);
        Integer quantity = dto.getQuantity();

        if (!this.productOrderService.exists(dto.getProductId(), cart.getId()))
        {
            this.productOrderService.create(quantity, dto.getProductId(), cart.getId(), ProductOrder.class);
        }
        else
        {
            final ProductOrder productOrder = this.productOrderService.getByProductIdAndOrderId(dto.getProductId(), cart.getId(), ProductOrder.class);
            quantity += productOrder.getQuantity();
            this.productOrderService.update(quantity, productOrder.getProduct().getId(), productOrder.getOrder().getId(), ProductOrder.class);
        }
    }


    @Override
    public void removeProduct(Integer userId, Integer productId)
    {
        final Order cart = this.getOrCreate(userId, Order.class);
        this.productOrderService.remove(productId, cart.getId());
    }


    private OrderDto populateOrderDto(final Integer userId, final OrderStatus status)
    {
        final UserDto userDto = this.userService.getById(userId, UserDto.class);

        final OrderDto orderDto = new OrderDto();
        orderDto.setUser(userDto);
        orderDto.setStatus(status);
        return orderDto;
    }


    @Override
    public void update(Integer userId, List<ProductOrderFormDto> productOrderDtos)
    {
        final Order cart = this.userService.getCart(userId, Order.class);
        this.productOrderService.updateAll(cart.getId(), productOrderDtos, ProductOrder.class);
    }


    @Override
    public <T> T getOrCreate(Integer userId, Class<T> type)
    {
        Order order = null;

        if (!this.userService.hasCart(userId))
        {
            final OrderDto orderDto = this.populateOrderDto(userId, OrderStatus.CART);
            order = this.orderService.create(orderDto, Order.class);
        }
        else
        {
            order = this.userService.getCart(userId, Order.class);
        }

        return this.mapper.map(order, type);
    }

}
