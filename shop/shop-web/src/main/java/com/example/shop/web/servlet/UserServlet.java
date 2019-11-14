/*
 * UserServlet.java
 *
 * created at 2019-11-11 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.shop.web.controller.UserController;
import com.example.shop.web.handler.Handler;
import com.example.shop.web.handler.RequestHandler;


@WebServlet("/user/*")
public class UserServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private Handler requestHandler;


    @Override
    public void init() throws ServletException
    {
        this.requestHandler = new RequestHandler(new UserController());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.requestHandler.handle(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.requestHandler.handle(req, resp);
    }

}
