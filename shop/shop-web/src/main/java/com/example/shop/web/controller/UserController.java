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

import com.example.shop.base.constants.SuccessMessage;
import com.example.shop.base.dto.MessageDto;
import com.example.shop.base.dto.UserFormDto;
import com.example.shop.base.dto.UserProfileDto;
import com.example.shop.base.dto.UserViewDto;
import com.example.shop.base.service.UserService;
import com.example.shop.web.annotation.Request;
import com.example.shop.web.annotation.WebController;
import com.example.shop.web.constants.ShopEndpoint;
import com.example.shop.web.constants.RequestMethod;


@WebController(path = "/user")
@Service(classes = Controller.class)
@Bean(id = "userController")
public class UserController extends BaseController
{
    @Inject
    @Reference
    private UserService userService;


    @Request(endpoints = ShopEndpoint.USER_PROFILE)
    public void profileGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final UserViewDto currentUser = (UserViewDto)req.getSession().getAttribute("user");
        try
        {
            final UserProfileDto dto = this.userService.getByEmail(currentUser.getEmail(), UserProfileDto.class);
            req.setAttribute("userProfile", dto);
            this.forwardToJsp(ShopEndpoint.USER_PROFILE, req, resp);
        }
        catch (final IllegalArgumentException e)
        {
            this.forwardToError(req, resp);
        }
    }


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.USER_EDIT)
    public void editPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final UserFormDto dto = this.mapper.map(req, UserFormDto.class);
        MessageDto messageDto = null;

        try
        {
            final UserViewDto userViewDto = this.userService.update(dto, UserViewDto.class);
            req.getSession().setAttribute("user", userViewDto);
            messageDto = new MessageDto(true, SuccessMessage.PROFILE_EDIT_SUCCESSFUL);
        }
        catch (final IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        req.setAttribute("message", messageDto);
        this.profileGet(req, resp);
    }


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.USER_DELETE)
    public void deletePost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final UserViewDto userViewDto = this.userService.getByEmail(req.getParameter("email"), UserViewDto.class);
        this.userService.remove(userViewDto.getId());

        req.getSession().invalidate();
        this.forwardToHome(req, resp);
    }

}
