/*
 * AuthenticationController.java
 *
 * created at 2019-11-12 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.shop.base.dto.UserViewDto;
import com.example.shop.base.dto.UserLoginDto;
import com.example.shop.base.dto.MessageDto;
import com.example.shop.base.dto.UserFormDto;
import com.example.shop.base.service.AuthenticationService;
import com.example.shop.web.annotation.Endpoint;
import com.example.shop.web.util.ServiceUtil;
import com.example.shop.web.annotation.WebController;


@WebController(path = "/auth")
public class AuthenticationController extends BaseController
{
    private AuthenticationService authenticationService;


    public AuthenticationController()
    {
        this.authenticationService = ServiceUtil.getService(AuthenticationController.class, AuthenticationService.class);
    }


    @Endpoint(urls = "/auth/login")
    public void loginGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.redirectToJsp(req, resp);
    }


    @Endpoint(urls = "/auth/register")
    public void registerGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", urls = "/auth/register")
    public void registerPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final UserFormDto dto = this.mapper.map(req, UserFormDto.class);
        MessageDto messageDto = null;

        try
        {
            final UserViewDto userViewDto = this.authenticationService.register(dto);
            req.getSession().setAttribute("user", userViewDto);
        }
        catch (IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
            req.setAttribute("message", messageDto);
        }

        this.redirectToHome(req, resp);

    }


    @Endpoint(method = "post", urls = "/auth/login")
    public void loginPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final UserLoginDto loginDto = this.mapper.map(req, UserLoginDto.class);
        MessageDto messageDto = null;

        try
        {
            final UserViewDto userViewDto = this.authenticationService.login(loginDto);
            req.getSession().setAttribute("user", userViewDto);
        }
        catch (IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
            req.setAttribute("message", messageDto);
        }

        this.redirectToHome(req, resp);
    }


    @Endpoint(urls = "/auth/logout")
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getSession().invalidate();
        this.redirectToHome(req, resp);
    }

}
