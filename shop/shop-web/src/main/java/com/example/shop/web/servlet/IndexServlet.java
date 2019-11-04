/*
 * HomeServlet.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.servlet;


import java.io.IOException;

import com.example.shop.base.service.CategoryService;
import org.apache.aries.blueprint.annotation.service.Reference;
import org.apache.aries.blueprint.annotation.service.Service;
import org.apache.aries.blueprint.annotation.service.ServiceProperty;

import javax.inject.Inject;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service(classes = Servlet.class, //
                properties = {@ServiceProperty(name = "osgi.http.whiteboard.context.select", values = "(osgi.http.whiteboard.context.name=online-shop)"), //
                              @ServiceProperty(name = "osgi.http.whiteboard.servlet.pattern", values = "/home")})
public class IndexServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Inject
    @Reference
    private CategoryService categoryService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
    }

}
