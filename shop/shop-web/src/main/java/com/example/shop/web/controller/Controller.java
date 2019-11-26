package com.example.shop.web.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Controller
{
    void redirectToHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;


    void redirectToError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;


    void redirectToJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;


    void redirectToJsp(String endpointPath, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;


    void writeObject(Object src, HttpServletResponse resp) throws ServletException, IOException;

}
