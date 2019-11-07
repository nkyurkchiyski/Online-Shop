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
import org.osgi.service.component.annotations.ServiceScope;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;


@Component(service = Servlet.class, scope = ServiceScope.PROTOTYPE, //
                property = {"osgi.http.whiteboard.context.select=(osgi.http.whiteboard.context.name=online-shop)", //
                            "osgi.http.whiteboard.servlet.pattern=/home"})
public class IndexServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Reference
    CategoryService categoryService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher rd = super.getServletContext().getNamedDispatcher("jsp");
        rd.forward(new HttpServletRequestFilter(request, "/jsp/home.jsp"), response);
    }

    private static class HttpServletRequestFilter extends HttpServletRequestWrapper
    {

        private String pathInfo;


        public HttpServletRequestFilter(HttpServletRequest request, String pathInfo)
        {
            super(request);
            this.pathInfo = pathInfo;
        }


        public String getServletPath()
        {
            return "/online-shop";
        }


        public String getPathInfo()
        {
            return pathInfo;
        }

    }

}
