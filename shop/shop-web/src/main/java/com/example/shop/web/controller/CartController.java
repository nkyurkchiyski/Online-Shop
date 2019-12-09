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
import com.example.shop.web.annotation.Request;
import com.example.shop.web.annotation.WebController;
import com.example.shop.web.constants.ShopEndpoint;
import com.example.shop.web.constants.RequestMethod;
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


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.CART_UPDATE)
    public void updateCartPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final ProductOrderFormDto[] productOrderDtos = this.gson.fromJson(req.getReader(), ProductOrderFormDto[].class);
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        MessageDto messageDto = null;

        try
        {
            this.cartService.update(userDto.getId(), Arrays.asList(productOrderDtos));
            messageDto = new MessageDto(true, SuccessMessage.CART_UPDATE_SUCCESSFUL);
        }
        catch (final IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        this.writeObject(messageDto, resp);
    }


    @Request(endpoints = ShopEndpoint.CART_DETAILS)
    public void cartGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
        final boolean hasCart = this.userService.hasCart(userDto.getId());
        OrderDetailsDto cartDto = null;

        if (hasCart)
        {
            cartDto = this.userService.getCart(userDto.getId(), OrderDetailsDto.class);
        }

        req.setAttribute("cart", cartDto);
        this.forwardToJsp(req, resp);
    }


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.CART_PRODUCT)
    public void addProductToCartPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        MessageDto messageDto = null;

        try
        {
            final ProductOrderFormDto dto = this.gson.fromJson(req.getReader(), ProductOrderFormDto.class);
            final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
            this.cartService.addProduct(userDto.getId(), dto);
            messageDto = new MessageDto(true, SuccessMessage.CART_PRODUCT_ADD_SUCCESSFUL);
        }
        catch (final IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        this.writeObject(messageDto, resp);
    }


    @Request(method = RequestMethod.DELETE, endpoints = ShopEndpoint.CART_PRODUCT)
    public void removeProductFromCartDelete(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        MessageDto messageDto = null;

        try
        {
            final Integer productId = Integer.parseInt(req.getParameter("productId"));
            final UserViewDto userDto = (UserViewDto)req.getSession().getAttribute("user");
            this.cartService.removeProduct(userDto.getId(), productId);
            messageDto = new MessageDto(true, SuccessMessage.CART_PRODUCT_REMOVE_SUCCESSFUL);
        }
        catch (final IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        this.writeObject(messageDto, resp);
    }

}
