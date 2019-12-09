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
    private static final int PAGE_SIZE = 6;

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
        final Order order = this.mapper.map(dto, Order.class);
        this.orderDao.save(order);
        return this.mapper.map(order, type);
    }


    @Override
    public <T> T getById(Integer id, Class<T> type)
    {
        return this.mapper.map(this.orderDao.findById(id), type);
    }


    @Override
    public <T> List<T> getAll(Class<T> type)
    {
        return this.mapOrders(this.orderDao.findAllPlaced(), type);
    }


    @Override
    public <T> List<T> getAllByUserId(Integer userId, Class<T> type)
    {
        return this.mapOrders(this.orderDao.findAllPlacedByUserId(userId), type);
    }


    @Override
    public void placeOrder(Integer userId, List<ProductOrderFormDto> productOrderDtos)
    {
        final Order cart = this.userService.getCart(userId, Order.class);
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

        order.setStatus(OrderStatus.APPROVED);
        order.setApprovedOn(LocalDateTime.now());
        this.orderDao.update(order);
    }


    @Override
    public byte[] generateInvoice(Integer orderId) throws IOException, IllegalArgumentException, IllegalAccessException
    {
        final OrderInvoiceDto orderDto = this.getById(orderId, OrderInvoiceDto.class);
        return this.invoiceService.generate(orderDto);
    }


    @Override
    public int getAllPages()
    {
        final int size = Math.toIntExact(this.orderDao.size());
        return this.calculatePages(size);
    }


    @Override
    public <T> List<T> getAllPaginated(int pageNumber, Class<T> type)
    {
        final int size = Math.toIntExact(this.orderDao.size());
        final int firstResult = this.getFirstResult(pageNumber, size);

        final List<Order> orders = this.orderDao.findAllPaginated(firstResult, PAGE_SIZE);
        return this.mapOrders(orders, type);
    }


    @Override
    public int getAllPlacedPages()
    {
        final int size = Math.toIntExact(this.orderDao.sizeAllPlaced());
        return this.calculatePages(size);
    }


    @Override
    public <T> List<T> getAllPlacedPaginated(int pageNumber, Class<T> type)
    {
        final int size = Math.toIntExact(this.orderDao.sizeAllPlaced());
        final int firstResult = this.getFirstResult(pageNumber, size);

        final List<Order> orders = this.orderDao.findAllPlacedPaginated(firstResult, PAGE_SIZE);
        return this.mapOrders(orders, type);
    }


    @Override
    public int getAllByUserIdPages(Integer userId)
    {
        final int size = Math.toIntExact(this.orderDao.sizeAllPlacedByUserId(userId));
        return this.calculatePages(size);
    }


    @Override
    public <T> List<T> getAllByUserIdPaginated(Integer userId, int pageNumber, Class<T> type)
    {
        final int size = Math.toIntExact(this.orderDao.sizeAllPlacedByUserId(userId));
        final int firstResult = this.getFirstResult(pageNumber, size);

        final List<Order> orders = this.orderDao.findAllPlacedByUserIdPaginated(userId, firstResult, PAGE_SIZE);
        return this.mapOrders(orders, type);
    }


    private <T> List<T> mapOrders(final List<Order> orders, final Class<T> type)
    {
        return orders.stream()
                     .map(source -> this.mapper.map(source, type))//
                     .collect(Collectors.toList());
    }


    private int calculatePages(final int size)
    {
        final int remainder = size % PAGE_SIZE;
        final int pages = size / PAGE_SIZE;
        return remainder == 0 ? pages : pages + 1;
    }


    private int getFirstResult(int pageNumber, final int size)
    {
        int firstResult = pageNumber * PAGE_SIZE;
        return firstResult > size || firstResult < 0 ? 0 : firstResult;
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
