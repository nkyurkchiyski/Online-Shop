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
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.shop.base.dto.MessageDto;
import com.example.shop.web.constants.ShopEndpoint;
import com.example.shop.web.constants.ShopUrl;
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
    public void forwardToHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final MessageDto messageDto = (MessageDto)req.getAttribute("message");
        if (messageDto == null || messageDto.isSuccessful())
        {
            this.forwardToJsp(ShopEndpoint.HOME, req, resp);
        }
        else
        {
            this.forwardToJsp(req, resp);
        }
    }


    @Override
    public void forwardToError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.forwardToJsp(ShopEndpoint.ERROR, req, resp);
    }


    @Override
    public void forwardToJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final String endpointPath = EndpointUtil.getPath(req.getRequestURI(), req.getContextPath());
        req.getRequestDispatcher(String.format("/jsp%s.jsp", endpointPath)).forward(req, resp);
    }


    @Override
    public void forwardToJsp(String endpointPath, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher(String.format("/jsp%s.jsp", endpointPath)).forward(req, resp);
    }


    @Override
    public void writeObject(Object src, HttpServletResponse resp) throws ServletException, IOException
    {
        final String data = this.gson.toJson(src);
        final PrintWriter out = resp.getWriter();

        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        out.print(data);
        out.flush();

    }


    @Override
    public int getCurrentPage(HttpServletRequest req)
    {
        final String pageNumberString = req.getParameter("page");
        final int pageNumber = pageNumberString == null ? 0 : Integer.parseInt(pageNumberString);
        return pageNumber;
    }


    @Override
    public void validatePage(HttpServletResponse resp, int pageNumber, int pages) throws IOException
    {
        if (pageNumber < 0 || (pages != 0 && pageNumber >= pages))
        {
            resp.sendRedirect(ShopUrl.ERROR);
        }
    }


    @Override
    public int getPageNumber(int pageNumber)
    {
        return pageNumber < 0 ? 0 : pageNumber;
    }

}
