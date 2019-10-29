/*
 * HomeServlet.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.shop.base.model.Category;
import com.example.shop.base.service.CategoryService;


public class HomeServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private transient CategoryService categoryService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final PrintWriter writer = resp.getWriter();
        showCategories(writer);
        super.doGet(req, resp);
    }


    private void showCategories(PrintWriter writer)
    {
        writer.println("<h1>Categories</h1>");
        List<Category> categories = categoryService.getAll();
        for (Category category : categories)
        {
            writer.println("<a" + category.getId() + "\">" + category.getName() + "</a><BR/>");
        }
    }


    public void setCategoryService(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

}
