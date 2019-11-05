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
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component(service = Servlet.class, //
                property = {"osgi.http.whiteboard.context.select:String=(osgi.http.whiteboard.context.name=online-shop)", //
                            "osgi.http.whiteboard.servlet.pattern:String=/home"})
public class IndexServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Reference
    private CategoryService categoryService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
    }

}
