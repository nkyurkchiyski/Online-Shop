/*
 * AuthenticationServlet.java
 *
 * created at 2019-11-06 by n.kyurkchiyski <YOURMAILADDRESS>
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


@WebServlet("/auth/*")
public class AuthenticationServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.processRequest(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");

        req.setAttribute("email", email);
        req.setAttribute("pass", password);

        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }


    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        req.getRequestDispatcher(String.format("/jsp%s.jsp", req.getRequestURI())).forward(req, resp);
    }
}
