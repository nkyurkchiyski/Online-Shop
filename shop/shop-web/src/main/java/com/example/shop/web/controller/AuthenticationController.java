/*
 * AuthenticationController.java
 *
 * created at 2019-11-12 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.shop.base.dto.UserDto;
import com.example.shop.base.dto.UserLoginDto;
import com.example.shop.base.service.AuthenticationService;
import com.example.shop.web.mapper.FormMapper;
import com.example.shop.web.mapper.Mapper;
import com.example.shop.web.util.Endpoint;
import com.example.shop.web.util.ServiceUtil;


public class AuthenticationController extends AbstractController {
    private AuthenticationService authenticationService;
    private Mapper mapper;

    public AuthenticationController() {
        this.authenticationService = ServiceUtil.getService(AuthenticationController.class, AuthenticationService.class);
        this.mapper = new FormMapper();
    }


    @Endpoint(path = "/auth/login")
    public void loginGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.redirectToJsp(req, resp);
    }


    @Endpoint(path = "/auth/register")
    public void registerGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", path = "/auth/register")
    public void registerPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO
        this.redirectToHome(req, resp);
    }


    @Endpoint(method = "post", path = "/auth/login")
    public void loginPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLoginDto loginDto = this.mapper.map(req, UserLoginDto.class);
        final UserDto userDto = this.authenticationService.login(loginDto);

        req.getSession().setAttribute("username", userDto.getEmail());
        req.getSession().setAttribute("isAdmin", userDto.isAdmin());
        this.redirectToHome(req, resp);
    }


    @Endpoint(path = "/auth/logout")
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        this.redirectToHome(req, resp);
    }

}
