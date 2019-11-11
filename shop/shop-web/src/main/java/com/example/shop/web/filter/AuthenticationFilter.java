/*
 * AuthenticationFilter.java
 *
 * created at 2019-11-11 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.shop.base.service.UserService;


@WebFilter({"/", "/register", "/login"})
public class AuthenticationFilter implements Filter
{
    private UserService userService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        if (req.getSession().getAttribute("username") != null)
        {
            resp.sendRedirect("/home");
        }
        else
        {
            chain.doFilter(req, resp);
        }
    }


    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

}
