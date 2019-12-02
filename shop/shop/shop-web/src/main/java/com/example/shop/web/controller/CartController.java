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

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Reference;
import org.apache.aries.blueprint.annotation.service.Service;

import com.example.shop.base.dto.MessageDto;
import com.example.shop.base.dto.OrderDetailsDto;
import com.example.shop.base.dto.ProductOrderFormDto;
import com.example.shop.base.dto.UserViewDto;
import com.example.shop.base.service.CartService;
import com.example.shop.base.service.UserService;
import com.example.shop.web.annotation.Endpoint;
import com.example.shop.web.annotation.WebController;
import com.example.shop.base.constants.SuccessMessage;


@WebController(path = "/cart")
@Service(classes = Controller.class)
@Bean(id = "cartController")
public class CartController extends BaseController
{
    @Inject
    @Reference
    private CartService cartService;

    @Inject
    @Reference
    private UserService userService;


    @Endpoint(method = "post", urls = "/cart/update")
    public void updateCartPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final ProductOrderFormDto[] productOrderDtos = this.gson.fromJson(req.getReader(), ProductOrderFormDto[].class);
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        MessageDto messageDto = null;

        try
        {
            this.cartService.update(userDto.getId(), Arrays.asList(productOrderDtos));
            messageDto = new MessageDto(true, SuccessMessage.CART_UPDATE_SUCCESSFUL);
        }
        catch (IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        this.writeObject(messageDto, resp);
    }


    @Endpoint(urls = "/cart/details")
    public void cartGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        final OrderDetailsDto cartDto = this.userService.getCart(userDto.getId(), OrderDetailsDto.class);

        req.setAttribute("cart", cartDto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", urls = "/cart/product")
    public void addProductToCartPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final ProductOrderFormDto dto = this.gson.fromJson(req.getReader(), ProductOrderFormDto.class);
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        MessageDto messageDto = null;

        try
        {
            this.cartService.addProduct(userDto.getId(), dto);
            messageDto = new MessageDto(true, SuccessMessage.CART_PRODUCT_ADD_SUCCESSFUL);
        }
        catch (IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        this.writeObject(messageDto, resp);
    }


    @Endpoint(method = "delete", urls = "/cart/product")
    public void removeProductFromCartDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer productId = Integer.parseInt(req.getParameter("productId"));
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");

        MessageDto messageDto = null;

        try
        {
            this.cartService.removeProduct(userDto.getId(), productId);
            messageDto = new MessageDto(true, SuccessMessage.CART_PRODUCT_REMOVE_SUCCESSFUL);
        }
        catch (IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        this.writeObject(messageDto, resp);
    }

}
