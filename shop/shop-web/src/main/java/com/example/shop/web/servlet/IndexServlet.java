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

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.ops4j.pax.cdi.api.OsgiService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;


@OsgiServiceProvider(classes = {Servlet.class})
@Properties({@Property(name = "alias", value = "/online-shop/home")})
@Singleton
public class IndexServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Inject
    @OsgiService
    private CategoryService categoryService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/jsp/index.jsp").include(req, resp);
    }


    public void setCategoryService(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

}
