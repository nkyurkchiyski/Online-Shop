package com.example.shop.web.servlet;


import com.example.shop.web.handler.Handler;
import com.example.shop.web.handler.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/home", "/error", "/order/*", "/product/*", "/category/*", "/user/*", "/auth/*", "/cart/*"})
public class WebAppServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private Handler requestHandler;


    @Override
    public void init() throws ServletException
    {
        this.requestHandler = RequestHandler.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.requestHandler.handle(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.requestHandler.handle(req, resp);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.requestHandler.handle(req, resp);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.requestHandler.handle(req, resp);
    }

}
