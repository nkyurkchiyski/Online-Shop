/*
 * ErrorController.java
 *
 * created at 2019-11-26 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;

import com.example.shop.web.annotation.Endpoint;
import com.example.shop.web.annotation.WebController;


@WebController(path = "/error")
@Service(classes = Controller.class)
@Bean(id = "errorController")
public class ErrorController extends BaseController
{
    @Endpoint(urls = "/error")
    public void errorGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.redirectToError(req, resp);
    }
}
