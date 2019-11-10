/*
 * HomeServlet.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.servlet;


import java.io.IOException;

import com.example.shop.base.model.Category;
import com.example.shop.base.service.CategoryService;
import com.example.shop.web.util.ServiceUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        this.categoryService = ServiceUtil.getService(HomeServlet.class, CategoryService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Category category = this.categoryService.getAll().get(0);
        req.setAttribute("model", category.getName());
        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }
}
