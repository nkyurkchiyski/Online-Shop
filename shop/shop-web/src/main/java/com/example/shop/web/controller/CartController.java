/*
 * CartController.java
 *
 * created at 2019-11-21 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.controller;


import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.shop.base.dto.OrderDetailsDto;
import com.example.shop.base.dto.ProductOrderFormDto;
import com.example.shop.base.dto.UserViewDto;
import com.example.shop.base.service.OrderService;
import com.example.shop.web.annotation.Endpoint;
import com.example.shop.web.annotation.WebController;
import com.example.shop.web.util.ServiceUtil;


@WebController(path = "/cart")
public class CartController extends BaseController
{
    private OrderService orderService;


    public CartController()
    {
        this.orderService = ServiceUtil.getService(OrderController.class, OrderService.class);
    }


    @Endpoint(method = "post", urls = "/cart/update")
    public void updateCartPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final ProductOrderFormDto[] productOrderDtos = this.gson.fromJson(req.getReader(), ProductOrderFormDto[].class);
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");

        this.orderService.updateCart(userDto.getId(), Arrays.asList(productOrderDtos));
        this.writeObject(true, resp);
    }


    @Endpoint(urls = "/cart/details")
    public void cartGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        final OrderDetailsDto cartDto = this.orderService.getUserCart(userDto.getId(), OrderDetailsDto.class);

        req.setAttribute("cart", cartDto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", urls = "/cart/product")
    public void addProductToCartPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final ProductOrderFormDto dto = this.mapper.map(req, ProductOrderFormDto.class);
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");

        this.orderService.addProductToCart(userDto.getId(), dto);
        resp.sendRedirect("/online-shop/cart/details");
    }


    @Endpoint(method = "delete", urls = "/cart/product")
    public void removeProductFromCartDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer productId = Integer.parseInt(req.getParameter("productId"));
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");

        this.orderService.removeProductFromCart(userDto.getId(), productId);
        this.writeObject(true, resp);
    }

}
