/*
 * AuthenticationController.java
 *
 * created at 2019-11-12 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.controller;


import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Reference;
import org.apache.aries.blueprint.annotation.service.Service;

import com.example.shop.base.dto.UserViewDto;
import com.example.shop.base.dto.UserLoginDto;
import com.example.shop.base.dto.MessageDto;
import com.example.shop.base.dto.UserFormDto;
import com.example.shop.base.service.AuthenticationService;
import com.example.shop.web.annotation.Request;
import com.example.shop.web.annotation.WebController;
import com.example.shop.web.constants.ShopEndpoint;
import com.example.shop.web.constants.RequestMethod;


@WebController(path = "/auth")
@Service(classes = Controller.class)
@Bean(id = "authenticationController")
public class AuthenticationController extends BaseController
{
    @Inject
    @Reference
    private AuthenticationService authenticationService;


    @Request(endpoints = ShopEndpoint.AUTH_LOGIN)
    public void loginGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        this.forwardToJsp(req, resp);
    }


    @Request(endpoints = ShopEndpoint.AUTH_REGISTER)
    public void registerGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        this.forwardToJsp(req, resp);
    }


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.AUTH_REGISTER)
    public void registerPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final UserFormDto dto = this.mapper.map(req, UserFormDto.class);
        MessageDto messageDto = null;

        try
        {
            final UserViewDto userViewDto = this.authenticationService.register(dto);
            req.getSession().setAttribute("user", userViewDto);
        }
        catch (final IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
            req.setAttribute("message", messageDto);
        }

        this.forwardToHome(req, resp);

    }


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.AUTH_LOGIN)
    public void loginPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final UserLoginDto loginDto = this.mapper.map(req, UserLoginDto.class);
        MessageDto messageDto = null;

        try
        {
            final UserViewDto userViewDto = this.authenticationService.login(loginDto);
            req.getSession().setAttribute("user", userViewDto);
        }
        catch (final IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
            req.setAttribute("message", messageDto);
        }

        this.forwardToHome(req, resp);
    }


    @Request(endpoints = ShopEndpoint.AUTH_LOGOUT)
    public void logout(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        req.getSession().invalidate();
        this.forwardToHome(req, resp);
    }

}
