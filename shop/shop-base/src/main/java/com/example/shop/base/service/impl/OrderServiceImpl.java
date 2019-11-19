/*
 * OrderServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import com.example.shop.base.dao.OrderDao;
import com.example.shop.base.dto.OrderDto;
import com.example.shop.base.dto.ProductOrderFormDto;
import com.example.shop.base.dto.UserDto;
import com.example.shop.base.model.Order;
import com.example.shop.base.model.OrderStatus;
import com.example.shop.base.model.Product;
import com.example.shop.base.model.ProductOrder;
import com.example.shop.base.model.User;
import com.example.shop.base.service.OrderService;
import com.example.shop.base.service.ProductService;
import com.example.shop.base.service.UserService;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;


@Service(classes = OrderService.class)
@Bean(id = "orderService")
public class OrderServiceImpl implements OrderService
{
    @Inject
    private OrderDao orderDao;

    @Inject
    private UserService userService;

    @Inject
    private ProductService productService;

    private ModelMapper mapper;


    public OrderServiceImpl()
    {
        this.mapper = new ModelMapper();
    }


    @Override
    public <T> T create(OrderDto dto, Class<T> type)
    {
        Order order = this.mapper.map(dto, Order.class);
        order = this.orderDao.save(order);
        return this.mapper.map(order, type);
    }


    @Override
    public <T> T getById(Integer id, Class<T> type)
    {
        final Order order = this.orderDao.findById(id);
        return this.mapper.map(order, type);
    }


    @Override
    public <T> List<T> getAll(Class<T> type)
    {
        final List<Order> orders = this.orderDao.findAll();
        return orders.stream()
                     .map(source -> this.mapper.map(source, type))//
                     .collect(Collectors.toList());
    }


    @Override
    public <T> T update(OrderDto dto, Class<T> type)
    {
        Order order = this.mapper.map(dto, Order.class);
        order = this.orderDao.update(order);
        return this.mapper.map(order, type);
    }


    @Override
    public void remove(Integer id)
    {
        final Order order = this.getById(id, Order.class);
        this.orderDao.delete(order);
    }


    @Override
    public void addProductToCart(Integer userId, ProductOrderFormDto dto)
    {
        // TODO add validation for quantity and exists
        final Product product = this.productService.getById(dto.getProductId(), Product.class);
        Order order = this.getUserCart(userId, Order.class);

        if (order == null)
        {
            final OrderDto orderDto = this.createOrderDto(userId, OrderStatus.CART);
            order = create(orderDto, Order.class);
        }

        ProductOrder productOrder = order.getProductOrder(product.getId());

        if (productOrder == null)
        {
            productOrder = this.createProductOrder(product, order);
        }

        productOrder.setQuantity(productOrder.getQuantity() + dto.getQuantity());

        order.addProduct(productOrder);
        this.orderDao.update(order);
    }


    @Override
    public void removeProductFromCart(Integer userId, Integer productId)
    {
        // TODO add validation
        final Product product = this.productService.getById(productId, Product.class);
        final Order order = this.getUserCart(userId, Order.class);
        final ProductOrder productOrder = order.getProductOrder(product.getId());

        order.removeProduct(productOrder);
        this.orderDao.update(order);
    }


    private ProductOrder createProductOrder(Product product, Order order)
    {
        final ProductOrder productOrder = new ProductOrder();
        productOrder.setProduct(product);
        productOrder.setQuantity(0);
        productOrder.setOrder(order);
        return productOrder;
    }


    @Override
    public <T> T getUserCart(Integer userId, Class<T> type)
    {
        final User user = this.userService.getById(userId, User.class);
        final Order cart = this.orderDao.findCartOfUser(user);

        if (cart == null)
        {
            return null;
        }

        return this.mapper.map(cart, type);
    }


    private OrderDto createOrderDto(Integer userId, OrderStatus status)
    {
        final UserDto userDto = this.userService.getById(userId, UserDto.class);
        final OrderDto orderDto = new OrderDto();
        orderDto.setUser(userDto);
        orderDto.setStatus(status);
        return orderDto;
    }


    @Override
    public <T> List<T> getAllByUserId(Integer userId, Class<T> type)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
