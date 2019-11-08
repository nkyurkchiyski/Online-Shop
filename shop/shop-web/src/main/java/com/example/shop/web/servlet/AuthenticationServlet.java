/*
 * AuthenticationServlet.java
 *
 * created at 2019-11-06 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.servlet;


import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;


public class AuthenticationServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.processRequest(req, resp);
    }


    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final String endpoint = this.getSpecificEndpoint(req.getRequestURI());

        req.getRequestDispatcher(String.format("/jsp/%s.jsp", endpoint)).forward(req, resp);
    }


    private String getSpecificEndpoint(final String uri)
    {
        final String[] tokens = uri.split("/");
        return tokens[tokens.length - 1];

    }

}
