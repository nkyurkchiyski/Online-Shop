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
    public <T> T getByProductIdAndOrderId(Integer productId, Integer orderId, Class<T> type)
    {
        ProductOrder productOrder = this.productOrderDao.find(productId, orderId);

        if (productOrder == null)
        {
            return null;
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

        this.validateProductOrder(product, order);
        this.validateQuantity(quantity, product);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setProduct(product);
        productOrder.setQuantity(quantity);
        productOrder.setOrder(order);

        productOrder = this.productOrderDao.update(productOrder);
        return this.mapper.map(productOrder, type);
    }


    private void validateProductOrder(final Product product, final Order order)
    {
        if (product == null)
        {
            throw new IllegalArgumentException("Product does not exist!");
        }

        if (order == null)
        {
            throw new IllegalArgumentException("Order does not exist!");
        }

    }


    private void validateQuantity(final Integer quantity, final Product product)
    {
        if (quantity == null || quantity <= 0)
        {
            throw new IllegalArgumentException("Invalid product quantity!");
        }

        if (quantity > product.getQuantity())
        {
            throw new IllegalArgumentException(String.format("For Product:%s we have only %s units left!", //
                                                             product.getName(), //
                                                             product.getQuantity()));
        }
    }

}
