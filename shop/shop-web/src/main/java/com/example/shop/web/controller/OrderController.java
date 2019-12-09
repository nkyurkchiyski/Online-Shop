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
import com.example.shop.web.annotation.Request;
import com.example.shop.web.annotation.WebController;
import com.example.shop.web.constants.ShopEndpoint;
import com.example.shop.web.constants.ShopUrl;
import com.example.shop.web.constants.RequestMethod;
import com.example.shop.base.constants.SuccessMessage;
import com.google.gson.JsonParseException;


@WebController(path = "/order")
@Service(classes = Controller.class)
@Bean(id = "orderController")
public class OrderController extends BaseController
{
    @Inject
    @Reference
    private OrderService orderService;


    @Request(endpoints = ShopEndpoint.ORDER_MY)
    public void myOrdersGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
            final int pageNumber = this.getCurrentPage(req);
            final int pages = this.orderService.getAllByUserIdPages(userDto.getId());

            this.validatePage(resp, pageNumber, pages);
            final List<OrderViewDto> dtos = this.orderService.getAllByUserIdPaginated(userDto.getId(), this.getPageNumber(pageNumber), OrderViewDto.class);
            this.setRequestAttributes(req, pageNumber, pages, dtos);

            this.forwardToJsp(req, resp);
        }
        catch (final IllegalArgumentException e)
        {
            resp.sendRedirect(ShopUrl.ERROR);
        }
    }


    @Request(endpoints = ShopEndpoint.ORDER_MANAGE)
    public void manageOrdersGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            final int pageNumber = this.getCurrentPage(req);
            final int pages = this.orderService.getAllPlacedPages();

            this.validatePage(resp, pageNumber, pages);
            final List<OrderViewDto> dtos = this.orderService.getAllPlacedPaginated(this.getPageNumber(pageNumber), OrderViewDto.class);
            this.setRequestAttributes(req, pageNumber, pages, dtos);

            this.forwardToJsp(ShopEndpoint.ORDER_MANAGE, req, resp);
        }
        catch (final IllegalArgumentException e)
        {
            resp.sendRedirect(ShopUrl.ERROR);
        }
    }


    @Request(endpoints = ShopEndpoint.ORDER_DETAILS)
    public void detailsGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            final Integer orderId = Integer.parseInt(req.getParameter("id"));
            final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
            final OrderDetailsDto orderDto = this.orderService.getById(orderId, OrderDetailsDto.class);

            if (!userDto.getId().equals(orderDto.getUser().getId()))
            {
                this.forwardToHome(req, resp);
            }

            req.setAttribute("order", orderDto);
            this.forwardToJsp(req, resp);
        }
        catch (final IllegalArgumentException e)
        {
            this.forwardToError(req, resp);
        }
    }


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.ORDER_PLACE)
    public void placeOrderPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final ProductOrderFormDto[] productOrderDtos = this.gson.fromJson(req.getReader(), ProductOrderFormDto[].class);
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        MessageDto messageDto = null;

        try
        {
            this.orderService.placeOrder(userDto.getId(), Arrays.asList(productOrderDtos));
            messageDto = new MessageDto(true, SuccessMessage.ORDER_PLACE_SUCCESSFUL);
        }
        catch (final IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        this.writeObject(messageDto, resp);

    }


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.ORDER_APPROVE)
    public void approveOrderPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        MessageDto messageDto = null;

        try
        {
            final Integer orderId = this.gson.fromJson(req.getReader(), Integer.class);
            this.orderService.approveOrder(orderId);
            messageDto = new MessageDto(true, SuccessMessage.ORDER_APPROVE_SUCCESSFUL);
        }
        catch (final IllegalArgumentException | JsonParseException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        this.writeObject(messageDto, resp);

    }


    @Request(endpoints = ShopEndpoint.ORDER_INVOICE)
    public void invoiceGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException, IllegalArgumentException, IllegalAccessException
    {
        try
        {
            final Integer orderId = Integer.parseInt(req.getParameter("id"));
            final byte[] output = this.orderService.generateInvoice(orderId);
            resp.addHeader("Content-Type", "application/force-download");
            resp.addHeader("Content-Disposition", String.format("attachment; filename=\"order%s.pdf\"", orderId));
            resp.getOutputStream().write(output);
        }
        catch (final IllegalArgumentException e)
        {
            this.forwardToError(req, resp);
        }
    }


    private void setRequestAttributes(final HttpServletRequest req, final int pageNumber, final Integer pages, final List<OrderViewDto> dtos)
    {
        req.setAttribute("currPage", pageNumber);
        req.setAttribute("pages", pages);
        req.setAttribute("orders", dtos);
    }

}
