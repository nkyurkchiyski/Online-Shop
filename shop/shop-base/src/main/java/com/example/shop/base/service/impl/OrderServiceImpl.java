/*
 * OrderServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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


    @Override
    public <T> List<T> getAllByUserId(Integer userId, Class<T> type)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void placeOrder(Integer userId, List<ProductOrderFormDto> productOrderDtos)
    {
        final Order cart = this.getUserCart(userId, Order.class);
        final Set<ProductOrder> productOrders = this.getUpdatedCartProducts(productOrderDtos, cart);
        final BigDecimal total = this.calculateTotal(productOrders);
        cart.setProducts(productOrders);
        cart.setStatus(OrderStatus.PENDING);
        cart.setOrderedOn(LocalDateTime.now());
        cart.setTotal(total);
        this.decreaseProductQuantities(productOrders);
        this.orderDao.update(cart);
    }


    private BigDecimal calculateTotal(Set<ProductOrder> productOrders)
    {
        BigDecimal total = new BigDecimal(0);
        for (ProductOrder po : productOrders)
        {
            total = total.add(po.getProduct().getPrice().multiply(new BigDecimal(po.getQuantity())));
        }
        return total;
    }


    private void decreaseProductQuantities(final Set<ProductOrder> productOrders)
    {
        for (final ProductOrder productOrder : productOrders)
        {
            this.productService.decreaseQuantity(productOrder.getProduct().getId(), productOrder.getQuantity());
        }
    }


    // TODO Move to cart Service
    @Override
    public void addProductToCart(Integer userId, ProductOrderFormDto dto)
    {
        final Product product = this.productService.getById(dto.getProductId(), Product.class);

        this.validateAddProductToCart(product, dto);

        final Order cart = getOrCreateCart(userId);
        final ProductOrder productOrder = this.getOrCreateProductOrder(product, cart);
        this.updateProductOrder(dto.getQuantity() + productOrder.getQuantity(), productOrder);

        cart.addProduct(productOrder);
        this.orderDao.update(cart);
    }


    // TODO Move to cart Service
    @Override
    public void removeProductFromCart(Integer userId, Integer productId)
    {
        final Product product = this.productService.getById(productId, Product.class);
        final Order order = this.getUserCart(userId, Order.class);

        this.validateRemoveProductFromCart(product, order);
        final ProductOrder productOrder = order.getProductOrder(product.getId());

        if (productOrder != null)
        {
            order.removeProduct(productOrder);
        }

        this.orderDao.update(order);
    }


    // TODO Move to cart Service
    @Override
    public void updateCart(Integer userId, List<ProductOrderFormDto> productOrderDtos)
    {
        final Order cart = this.getUserCart(userId, Order.class);
        final Set<ProductOrder> productOrders = this.getUpdatedCartProducts(productOrderDtos, cart);
        cart.setProducts(productOrders);
        this.orderDao.update(cart);
    }


    // TODO Move to cart Service
    private Set<ProductOrder> getUpdatedCartProducts(final List<ProductOrderFormDto> productOrderDtos, final Order cart)
    {
        final Set<ProductOrder> productOrders = new HashSet<ProductOrder>();

        if (cart != null)
        {
            for (ProductOrderFormDto dto : productOrderDtos)
            {
                final Product product = this.productService.getById(dto.getProductId(), Product.class);
                this.validateAddProductToCart(product, dto);
                final ProductOrder productOrder = this.getOrCreateProductOrder(product, cart);
                this.updateProductOrder(dto.getQuantity(), productOrder);
                productOrders.add(productOrder);
            }
        }

        return productOrders;
    }


    // TODO Move to cart Service
    private OrderDto createOrderDto(final Integer userId, final OrderStatus status)
    {
        final UserDto userDto = this.userService.getById(userId, UserDto.class);
        final OrderDto orderDto = new OrderDto();
        orderDto.setUser(userDto);
        orderDto.setStatus(status);
        return orderDto;
    }


    // TODO Move to ProductOrderService
    private ProductOrder createProductOrder(final Product product, final Order order)
    {
        final ProductOrder productOrder = new ProductOrder();
        productOrder.setProduct(product);
        productOrder.setQuantity(0);
        productOrder.setOrder(order);
        return productOrder;
    }


    // TODO Move to cart Service
    private Order getOrCreateCart(final Integer userId)
    {
        Order order = this.getUserCart(userId, Order.class);

        if (order == null)
        {
            final OrderDto orderDto = this.createOrderDto(userId, OrderStatus.CART);
            order = create(orderDto, Order.class);
        }
        return order;
    }


    // TODO Move to ProductOrderService
    private ProductOrder getOrCreateProductOrder(final Product product, final Order order)
    {
        ProductOrder productOrder = order.getProductOrder(product.getId());

        if (productOrder == null)
        {
            productOrder = this.createProductOrder(product, order);
        }

        return productOrder;
    }


    // TODO Move to ProductOrderService
    private ProductOrder updateProductOrder(final Integer quantity, final ProductOrder productOrder)
    {
        productOrder.setQuantity(quantity);
        return productOrder;
    }


    // TODO Move to cart Service
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


    // TODO Move to cart Service
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
