package com.example.shop.web.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Controller
{
    void forwardToHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;


    void forwardToError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;


    void forwardToJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;


    void forwardToJsp(String endpointPath, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;


    void writeObject(Object src, HttpServletResponse resp) throws ServletException, IOException;


    int getCurrentPage(HttpServletRequest req);


    int getPageNumber(int pageNumber);


    void validatePage(HttpServletResponse resp, int pageNumber, int pages) throws IOException;

}
