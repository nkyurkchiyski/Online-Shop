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


@WebFilter(urlPatterns ={"/user/*", "/category/*", "/order/*", "/product/*", "/auth/logout", "/cart/*"})
public class LoggedInFilter implements Filter
{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        final HttpServletRequest req = (HttpServletRequest)request;
        final HttpServletResponse resp = (HttpServletResponse)response;

        final UserViewDto userViewDto = (UserViewDto)req.getSession().getAttribute("user");

        if (userViewDto == null)
        {
            resp.sendRedirect(ShopUrl.LOGIN);
        }
        else
        {
            chain.doFilter(req, resp);
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }


    @Override
    public void destroy()
    {
    }
}
