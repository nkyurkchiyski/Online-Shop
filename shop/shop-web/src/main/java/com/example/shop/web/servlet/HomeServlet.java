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

import javax.servlet.ServletException;
import javax.servlet.http.*;


public class HomeServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private CategoryService categoryService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
    }


    public void setCategoryService(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

}
