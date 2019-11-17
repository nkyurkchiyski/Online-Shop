package com.example.shop.web.controller;

import com.example.shop.web.annotation.Endpoint;
import com.example.shop.web.annotation.WebController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebController(path = "/home")
public class HomeController extends BaseController {

    @Endpoint(urls = {"/", "/home"})
    public void homeGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.redirectToHome(req, resp);
    }
}
