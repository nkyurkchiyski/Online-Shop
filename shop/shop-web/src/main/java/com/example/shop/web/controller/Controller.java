package com.example.shop.web.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Controller
{
    public void redirectToHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;


    public void redirectToJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;


    public void writeObject(Object src, HttpServletResponse resp) throws ServletException, IOException;

}
