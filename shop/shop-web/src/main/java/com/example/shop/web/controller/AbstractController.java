/*
 * AbstractController.java
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

import com.example.shop.web.util.EndpointUtil;


public abstract class AbstractController implements Controller
{
    @Override
    public void redirectToHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.sendRedirect("/online-shop/home");
    }


    @Override
    public void redirectToJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final String endpointPath = EndpointUtil.getPath(req.getRequestURI(), req.getContextPath());
        req.getRequestDispatcher(String.format("/jsp%s.jsp", endpointPath)).forward(req, resp);
    }
}
