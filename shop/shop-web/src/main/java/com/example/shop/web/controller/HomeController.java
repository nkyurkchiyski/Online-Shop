package com.example.shop.web.controller;


import com.example.shop.web.annotation.Request;
import com.example.shop.web.annotation.WebController;
import com.example.shop.web.constants.ShopEndpoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;

import java.io.IOException;


@WebController(path = "/home")
@Service(classes = Controller.class)
@Bean(id = "homeController")
public class HomeController extends BaseController
{

    @Request(endpoints = ShopEndpoint.HOME)
    public void homeGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        this.forwardToHome(req, resp);
    }
}
