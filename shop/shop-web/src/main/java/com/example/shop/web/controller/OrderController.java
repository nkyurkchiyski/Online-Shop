/*
 * OrderController.java
 *
 * created at 2019-11-14 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.shop.base.dto.OrderViewDto;
import com.example.shop.base.dto.ProductOrderFormDto;
import com.example.shop.base.dto.UserViewDto;
import com.example.shop.base.service.OrderService;
import com.example.shop.web.annotation.Endpoint;
import com.example.shop.web.annotation.WebController;
import com.example.shop.web.util.ServiceUtil;


@WebController(path = "/order")
public class OrderController extends BaseController
{
    private OrderService orderService;


    public OrderController()
    {
        this.orderService = ServiceUtil.getService(OrderController.class, OrderService.class);
    }


    @Endpoint(method = "post", urls = "/order/product/add")
    public void addProductPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final ProductOrderFormDto dto = this.mapper.map(req, ProductOrderFormDto.class);
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");

        this.orderService.addProductToCart(userDto.getId(), dto);
        resp.sendRedirect("/online-shop/order/cart");
    }


    @Endpoint(method = "post", urls = "/order/product/remove")
    public void removeProductPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
      //TODO test
        final Integer productId = Integer.parseInt(req.getParameter("productId"));
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");

        this.orderService.removeProductFromCart(userDto.getId(), productId);
        resp.sendRedirect("/online-shop/order/cart");
    }


    @Endpoint(urls = "/order/my")
    public void myOrdersGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
      //TODO implement
        this.redirectToJsp(req, resp);
    }


    @Endpoint(urls = "/order/cart")
    public void cartGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        final OrderViewDto cartDto = this.orderService.getUserCart(userDto.getId(), OrderViewDto.class);

        req.setAttribute("cart", cartDto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(urls = "/order/all")
    public void allGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
      //TODO implement
        this.redirectToJsp(req, resp);
    }

}
