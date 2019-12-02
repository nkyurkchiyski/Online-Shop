/*
 * OrderController.java
 *
 * created at 2019-11-14 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.controller;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Reference;
import org.apache.aries.blueprint.annotation.service.Service;

import com.example.shop.base.dto.MessageDto;
import com.example.shop.base.dto.OrderDetailsDto;
import com.example.shop.base.dto.OrderViewDto;
import com.example.shop.base.dto.ProductOrderFormDto;
import com.example.shop.base.dto.UserViewDto;
import com.example.shop.base.service.OrderService;
import com.example.shop.web.annotation.Endpoint;
import com.example.shop.web.annotation.WebController;
import com.example.shop.base.constants.SuccessMessage;
import com.example.shop.web.util.ServiceUtil;


@WebController(path = "/order")
@Service(classes = Controller.class)
@Bean(id = "orderController")
public class OrderController extends BaseController
{
    @Inject
    @Reference
    private OrderService orderService;


    public OrderController()
    {
        this.orderService = ServiceUtil.getService(OrderController.class, OrderService.class);
    }


    @Endpoint(urls = "/order/my")
    public void myOrdersGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        final List<OrderViewDto> orderDtos = this.orderService.getAllByUserId(userDto.getId(), OrderViewDto.class);

        req.setAttribute("orders", orderDtos);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(urls = "/order/manage")
    public void allGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final List<OrderViewDto> orderDtos = this.orderService.getAll(OrderViewDto.class);

        req.setAttribute("orders", orderDtos);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(urls = "/order/details")
    public void detailsGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer orderId = Integer.parseInt(req.getParameter("id"));
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        final OrderDetailsDto orderDto = this.orderService.getById(orderId, OrderDetailsDto.class);

        if (orderDto != null && !userDto.getId().equals(orderDto.getUser().getId()))
        {
            this.redirectToHome(req, resp);
        }

        req.setAttribute("order", orderDto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", urls = "/order/place")
    public void placeOrderPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final ProductOrderFormDto[] productOrderDtos = this.gson.fromJson(req.getReader(), ProductOrderFormDto[].class);
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        MessageDto messageDto = null;

        try
        {
            this.orderService.placeOrder(userDto.getId(), Arrays.asList(productOrderDtos));
            messageDto = new MessageDto(true, SuccessMessage.ORDER_PLACE_SUCCESSFUL);
        }
        catch (IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        this.writeObject(messageDto, resp);

    }


    @Endpoint(method = "post", urls = "/order/approve")
    public void approveOrderPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer orderId = this.gson.fromJson(req.getReader(), Integer.class);
        MessageDto messageDto = null;

        try
        {
            this.orderService.approveOrder(orderId);
            messageDto = new MessageDto(true, SuccessMessage.ORDER_APPROVE_SUCCESSFUL);
        }
        catch (IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        this.writeObject(messageDto, resp);

    }


    @Endpoint(urls = "/order/invoice")
    public void invoiceGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalArgumentException, IllegalAccessException
    {
        final Integer orderId = Integer.parseInt(req.getParameter("id"));

        final byte[] output = this.orderService.generateInvoice(orderId);
        resp.addHeader("Content-Type", "application/force-download");
        resp.addHeader("Content-Disposition", String.format("attachment; filename=\"order%s.pdf\"", orderId));
        resp.getOutputStream().write(output);

    }

}
