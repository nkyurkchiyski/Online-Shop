/*
 * AbstractController.java
 *
 * created at 2019-11-12 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.shop.web.mapper.FormMapper;
import com.example.shop.web.mapper.Mapper;
import com.example.shop.web.util.EndpointUtil;
import com.google.gson.Gson;


public abstract class BaseController implements Controller
{
    protected final Mapper mapper;

    protected final Gson gson;


    public BaseController()
    {
        this.mapper = new FormMapper();
        this.gson = new Gson();
    }


    @Override
    public void redirectToHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }


    @Override
    public void redirectToJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final String endpointPath = EndpointUtil.getPath(req.getRequestURI(), req.getContextPath());
        req.getRequestDispatcher(String.format("/jsp%s.jsp", endpointPath)).forward(req, resp);
    }


    @Override
    public void writeObject(Object src, HttpServletResponse resp) throws ServletException, IOException
    {
        final String data = this.gson.toJson(src);
        final PrintWriter out = resp.getWriter();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        out.print(data);
        out.flush();

    }
}
