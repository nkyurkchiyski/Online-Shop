/*
 * OrderController.java
 *
 * created at 2019-11-14 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.google.gson.Gson;


@WebController(path = "/order")
public class OrderController extends BaseController
{
    private OrderService orderService;


    public OrderController()
    {
        this.orderService = ServiceUtil.getService(OrderController.class, OrderService.class);
    }


    @Endpoint(method = "post", urls = "/order/product/add")
    public void addProductToCartPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final ProductOrderFormDto dto = this.mapper.map(req, ProductOrderFormDto.class);
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");

        this.orderService.addProductToCart(userDto.getId(), dto);
        resp.sendRedirect("/online-shop/order/cart");
    }


    @Endpoint(method = "post", urls = "/order/product/remove")
    public void removeProductFromCartPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // TODO Change method to POST
        final Gson gson = new Gson();
        final Integer productId = gson.fromJson(req.getReader(), Integer.class);
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");

        this.orderService.removeProductFromCart(userDto.getId(), productId);
        resp.setStatus(HttpServletResponse.SC_OK);
    }


    @Endpoint(urls = "/order/my")
    public void myOrdersGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // TODO implement
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");

        this.redirectToJsp(req, resp);
    }


    @Endpoint(urls = "/order/cart")
    public void cartGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        final OrderDetailsDto cartDto = this.orderService.getUserCart(userDto.getId(), OrderDetailsDto.class);

        req.setAttribute("cart", cartDto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(urls = "/order/all")
    public void allGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // TODO implement
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", urls = "/order/place")
    public void placeOrderPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // TODO implement
        final Gson gson = new Gson();
        final ProductOrderFormDto[] productOrderDtos = gson.fromJson(req.getReader(), ProductOrderFormDto[].class);
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        this.orderService.placeOrder(userDto.getId(), Arrays.asList(productOrderDtos));
        this.redirectToHome(req, resp);
    }

}
