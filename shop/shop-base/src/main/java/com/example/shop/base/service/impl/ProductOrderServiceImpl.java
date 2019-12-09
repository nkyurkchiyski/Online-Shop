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

import com.example.shop.base.constants.ErrorMessage;
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
    public <T> T getByProductIdAndOrderId(Integer productId, Integer orderId, Class<T> type)
    {
        final ProductOrder productOrder = this.productOrderDao.findByProductIdAndOrderId(productId, orderId);
        return this.mapper.map(productOrder, type);
    }


    @Override
    public void remove(Integer productId, Integer orderId)
    {
        final ProductOrder productOrder = this.productOrderDao.findByProductIdAndOrderId(productId, orderId);
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
        final ProductOrder productOrder = this.productOrderDao.findByProductIdAndOrderId(productId, orderId);
        this.validateQuantity(quantity, productOrder.getProduct());
        productOrder.setQuantity(quantity);
        this.productOrderDao.update(productOrder);

        return this.mapper.map(productOrder, type);
    }


    @Override
    public <T> T create(Integer quantity, Integer productId, Integer orderId, Class<T> type)
    {
        final Product product = this.productService.getById(productId, Product.class);
        final Order order = this.orderDao.findById(orderId);

        this.validateQuantity(quantity, product);

        final ProductOrder productOrder = new ProductOrder();
        productOrder.setProduct(product);
        productOrder.setQuantity(quantity);
        productOrder.setOrder(order);

        this.productOrderDao.update(productOrder);
        return this.mapper.map(productOrder, type);
    }


    @Override
    public boolean exists(Integer productId, Integer orderId)
    {
        return this.productOrderDao.existsWithProductIdAndOrderId(productId, orderId);
    }


    private void validateQuantity(final Integer quantity, final Product product)
    {
        if (!product.isActive())
        {
            throw new IllegalArgumentException(String.format(ErrorMessage.PRODUCT_NOT_AVAILABLE, product.getName()));
        }

        if (quantity == null || quantity <= 0)
        {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_INVALID_QUANTITY);
        }

        if (quantity > product.getQuantity())
        {
            throw new IllegalArgumentException(String.format(ErrorMessage.PRODUCT_NOT_ENOUGH_QUANTITY, //
                                                             product.getName(), //
                                                             product.getQuantity()));
        }

    }

}
