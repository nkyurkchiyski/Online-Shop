/*
 * UserController.java
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

import com.example.shop.base.dto.UserProfileDto;
import com.example.shop.base.dto.UserViewDto;
import com.example.shop.base.service.UserService;
import com.example.shop.web.util.Endpoint;
import com.example.shop.web.util.ServiceUtil;


public class UserController extends AbstractController
{
    private UserService userService;


    public UserController()
    {
        this.userService = ServiceUtil.getService(UserController.class, UserService.class);
    }


    @Endpoint(path = "/user/profile")
    public void profileGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final UserViewDto currentUser = (UserViewDto)req.getSession().getAttribute("user");
        final UserProfileDto dto = this.userService.getByEmail(currentUser.getEmail(), UserProfileDto.class);

        req.setAttribute("userProfile", dto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", path = "/user/edit")
    public void editPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //TODO Add UserEditDto, map it from form, redirtect to profile page
        this.redirectToHome(req, resp);
    }

    @Endpoint(method = "post", path = "/user/delete")
    public void deletePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final UserViewDto currentUser = (UserViewDto)req.getSession().getAttribute("user");
        this.userService.remove(currentUser.getId());
        req.getSession().invalidate();
        this.redirectToHome(req, resp);
    }

}
