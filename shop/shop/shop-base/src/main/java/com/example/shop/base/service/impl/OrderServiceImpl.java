/*
 * OrderServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.example.shop.base.constants.ErrorMessage;
import com.example.shop.base.dao.OrderDao;
import com.example.shop.base.dto.OrderDto;
import com.example.shop.base.dto.OrderInvoiceDto;
import com.example.shop.base.dto.ProductOrderFormDto;
import com.example.shop.base.model.Order;
import com.example.shop.base.model.OrderStatus;
import com.example.shop.base.model.ProductOrder;
import com.example.shop.base.service.InvoiceService;
import com.example.shop.base.service.OrderService;
import com.example.shop.base.service.ProductOrderService;
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
    private ProductOrderService productOrderService;

    @Inject
    private ProductService productService;

    @Inject
    private InvoiceService invoiceService;

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

        if (order == null)
        {
            return null;
        }

        return this.mapper.map(order, type);
    }


    @Override
    public <T> List<T> getAll(Class<T> type)
    {
        final List<Order> orders = this.orderDao.findAllPlaced();
        return orders.stream()
                     .map(source -> this.mapper.map(source, type))//
                     .collect(Collectors.toList());
    }


    @Override
    public <T> List<T> getAllByUserId(Integer userId, Class<T> type)
    {
        final List<Order> orders = this.orderDao.findAllPlacedByUserId(userId);
        return orders.stream()
                     .map(source -> this.mapper.map(source, type))//
                     .collect(Collectors.toList());
    }


    @Override
    public void placeOrder(Integer userId, List<ProductOrderFormDto> productOrderDtos)
    {
        final Order cart = this.userService.getCart(userId, Order.class);

        if (cart == null)
        {
            throw new IllegalArgumentException(ErrorMessage.CART_DOES_NOT_EXIST);
        }

        final List<ProductOrder> productOrders = this.productOrderService.updateAll(cart.getId(), productOrderDtos, ProductOrder.class);
        final BigDecimal total = this.calculateTotal(productOrders);

        cart.setStatus(OrderStatus.PENDING);
        cart.setOrderedOn(LocalDateTime.now());
        cart.setTotal(total);

        this.decreaseProductQuantities(productOrders);
        this.orderDao.update(cart);
    }


    @Override
    public void approveOrder(Integer orderId)
    {
        final Order order = this.getById(orderId, Order.class);

        if (order == null)
        {
            throw new IllegalArgumentException(ErrorMessage.ORDER_DOES_NOT_EXIST);
        }

        order.setStatus(OrderStatus.APPROVED);
        order.setApprovedOn(LocalDateTime.now());
        this.orderDao.update(order);
    }


    @Override
    public byte[] generateInvoice(Integer orderId) throws IOException, IllegalArgumentException, IllegalAccessException
    {
        final OrderInvoiceDto orderDto = this.getById(orderId, OrderInvoiceDto.class);

        if (orderDto == null)
        {
            throw new IllegalArgumentException(ErrorMessage.ORDER_DOES_NOT_EXIST);
        }

        return this.invoiceService.generate(orderDto);
    }


    private BigDecimal calculateTotal(final Collection<ProductOrder> productOrders)
    {
        BigDecimal total = new BigDecimal(0);
        for (final ProductOrder po : productOrders)
        {
            total = total.add(po.getProduct().getPrice().multiply(new BigDecimal(po.getQuantity())));
        }
        return total;
    }


    private void decreaseProductQuantities(final Collection<ProductOrder> productOrders)
    {
        for (final ProductOrder productOrder : productOrders)
        {
            this.productService.decreaseQuantity(productOrder.getProduct().getId(), productOrder.getQuantity());
        }
    }

}
