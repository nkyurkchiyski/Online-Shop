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

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ops4j.pax.cdi.api.Component;
import org.ops4j.pax.cdi.api.Dynamic;
import org.ops4j.pax.cdi.api.Immediate;
import org.ops4j.pax.cdi.api.Service;


@WebServlet("/home")
@Component @Immediate
public class IndexServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Inject
    @Service @Dynamic
    CategoryService categoryService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        final Category category = this.categoryService.getById(1);
        request.setAttribute("model", "test");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/home.jsp");
        rd.forward(request, response);
    }
}
