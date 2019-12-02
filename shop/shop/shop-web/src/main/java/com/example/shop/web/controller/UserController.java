/*
 * UserController.java
 *
 * created at 2019-11-14 by n.kyurkchiyski <YOURMAILADDRESS>
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

import com.example.shop.base.dto.UserProfileDto;
import com.example.shop.base.dto.UserViewDto;
import com.example.shop.base.service.UserService;
import com.example.shop.web.annotation.Endpoint;
import com.example.shop.web.annotation.WebController;


@WebController(path = "/user")
@Service(classes = Controller.class)
@Bean(id = "userController")
public class UserController extends BaseController
{
    @Inject
    @Reference
    private UserService userService;


    @Endpoint(urls = "/user/profile")
    public void profileGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final UserViewDto currentUser = (UserViewDto)req.getSession().getAttribute("user");
        final UserProfileDto dto = this.userService.getByEmail(currentUser.getEmail(), UserProfileDto.class);

        req.setAttribute("userProfile", dto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", urls = "/user/edit")
    public void editPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // TODO Add UserEditDto, map it from form, redirect to profile page
        this.redirectToHome(req, resp);
    }


    @Endpoint(method = "post", urls = "/user/delete")
    public void deletePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final UserViewDto currentUser = (UserViewDto)req.getSession().getAttribute("user");
        this.userService.remove(currentUser.getId());
        req.getSession().invalidate();
        this.redirectToHome(req, resp);
    }

}
