/*
 * ProductOrderServiceImpl.java
 *
 * created at 2019-11-21 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.modelmapper.ModelMapper;

import com.example.shop.base.dao.OrderDao;
import com.example.shop.base.dao.ProductOrderDao;
import com.example.shop.base.dto.ProductOrderFormDto;
import com.example.shop.base.model.Order;
import com.example.shop.base.model.Product;
import com.example.shop.base.model.ProductOrder;
import com.example.shop.base.service.ProductOrderService;
import com.example.shop.base.service.ProductService;


@Service(classes = ProductOrderService.class)
@Bean(id = "productOrderService")
public class ProductOrderServiceImpl implements ProductOrderService
{

    @Inject
    private ProductService productService;

    @Inject
    private ProductOrderDao productOrderDao;

    @Inject
    private OrderDao orderDao;

    private ModelMapper mapper;


    public ProductOrderServiceImpl()
    {
        this.mapper = new ModelMapper();
    }


    @Override
    public <T> T getOrCreate(Integer productId, Integer orderId, Class<T> type)
    {
        ProductOrder productOrder = this.productOrderDao.find(productId, orderId);

        if (productOrder == null)
        {
            productOrder = this.create(0, productId, orderId, ProductOrder.class);
        }

        return this.mapper.map(productOrder, type);
    }


    @Override
    public void remove(Integer orderId, Integer productId)
    {
        final ProductOrder productOrder = this.productOrderDao.find(productId, orderId);
        this.productOrderDao.delete(productOrder);
    }


    @Override
    public <T> List<T> updateAll(Integer orderId, List<ProductOrderFormDto> productOrderDtos, Class<T> type)
    {
        final Set<ProductOrder> productOrders = new HashSet<ProductOrder>();
        for (ProductOrderFormDto dto : productOrderDtos)
        {
            final ProductOrder productOrder = this.update(dto.getQuantity(), dto.getProductId(), orderId, ProductOrder.class);
            productOrders.add(productOrder);
        }

        return productOrders.stream()
                            .map(source -> this.mapper.map(source, type))//
                            .collect(Collectors.toList());
    }


    @Override
    public <T> T update(Integer quantity, Integer productId, Integer orderId, Class<T> type)
    {
        final ProductOrder productOrder = this.productOrderDao.find(productId, orderId);

        if (productOrder == null)
        {
            return null;
        }

        productOrder.setQuantity(quantity);
        this.productOrderDao.update(productOrder);
        return this.mapper.map(productOrder, type);
    }


    @Override
    public <T> T create(Integer quantity, Integer productId, Integer orderId, Class<T> type)
    {
        final Product product = this.productService.getById(productId, Product.class);
        final Order order = this.orderDao.findById(orderId);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setProduct(product);
        productOrder.setQuantity(0);
        productOrder.setOrder(order);

        productOrder = this.productOrderDao.update(productOrder);
        return this.mapper.map(productOrder, type);
    }


    private void validateAddProductToCart(final Product product, final ProductOrderFormDto dto)
    {
        if (product == null)
        {
            throw new IllegalArgumentException("Product does not exist!");
        }

        if (dto.getQuantity() == null || dto.getQuantity() <= 0)
        {
            throw new IllegalArgumentException("Quantity cannot be below zero!");
        }

        if (dto.getQuantity() > product.getQuantity())
        {
            throw new IllegalArgumentException("Quantity exceeds present product units!");
        }
    }


    private void validateRemoveProductFromCart(final Product product, final Order order)
    {
        if (product == null)
        {
            throw new IllegalArgumentException("Product does not exist!");
        }

        if (order == null)
        {
            throw new IllegalArgumentException("Cart does not exist!");
        }
    }

}
