/*
 * AccountFilter.java
 *
 * created at 2019-12-05 by n.kyurkchiyski <YOURMAILADDRESS>
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

import com.example.shop.base.dto.UserViewDto;
import com.example.shop.web.constants.ShopUrl;


@WebFilter(urlPatterns = {"/user/delete", "/user/edit"})
public class ManageAccountFilter implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        final HttpServletRequest req = (HttpServletRequest)request;
        final HttpServletResponse resp = (HttpServletResponse)response;

        final String userEmail = req.getParameter("email");
        final UserViewDto userViewDto = (UserViewDto)req.getSession().getAttribute("user");

        if (userViewDto == null)
        {
            resp.sendRedirect(ShopUrl.LOGIN);
        }
        else if (!userViewDto.isAdmin() && !userEmail.equals(userViewDto.getEmail()))
        {
            resp.sendRedirect(ShopUrl.ERROR);
        }
        else
        {
            chain.doFilter(req, resp);
        }
    }


    @Override
    public void destroy()
    {
    }

}
