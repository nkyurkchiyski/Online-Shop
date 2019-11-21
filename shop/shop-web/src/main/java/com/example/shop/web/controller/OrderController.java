/*
 * OrderController.java
 *
 * created at 2019-11-14 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


    @Endpoint(urls = "/order/my")
    public void myOrdersGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // TODO implement
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");

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
        final ProductOrderFormDto[] productOrderDtos = this.gson.fromJson(req.getReader(), ProductOrderFormDto[].class);
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        this.orderService.placeOrder(userDto.getId(), Arrays.asList(productOrderDtos));

        this.writeObject(true, resp);

    }

}
