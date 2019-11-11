/*
 * AbstractController.java
 *
 * created at 2019-11-11 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.handler;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class AbstractHandler implements Handler
{
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String jspPath = "/jsp/home.jsp";
        if (req.getMethod().equalsIgnoreCase("get"))
        {
            jspPath = String.format("/jsp%s.jsp", req.getRequestURI());

        }
        else
        {

        }

        req.getRequestDispatcher(jspPath).forward(req, resp);

    }


    protected String getCrudOperation(final String uri)
    {
        final String[] tokens = uri.split("/");
        return tokens[tokens.length - 1];
    }

}
