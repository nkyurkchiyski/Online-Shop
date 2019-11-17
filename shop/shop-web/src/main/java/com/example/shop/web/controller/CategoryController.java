/*
 * CategoryController.java
 *
 * created at 2019-11-14 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.shop.base.dto.CategoryDto;
import com.example.shop.base.dto.CategoryProductsDto;
import com.example.shop.base.service.CategoryService;
import com.example.shop.web.annotation.Endpoint;
import com.example.shop.web.util.ServiceUtil;
import com.example.shop.web.annotation.WebController;


@WebController(path = "/category")
public class CategoryController extends BaseController
{
    private CategoryService categoryService;


    public CategoryController()
    {
        this.categoryService = ServiceUtil.getService(CategoryController.class, CategoryService.class);
    }


    @Endpoint(urls = "/category/create")
    public void createGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", urls = "/category/create")
    public void createPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        CategoryDto dto = this.mapper.map(req, CategoryDto.class);
        dto = this.categoryService.create(dto, CategoryDto.class);
        this.redirectToHome(req, resp);
    }


    @Endpoint(urls = "/category/edit")
    public void editGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final CategoryDto dto = this.categoryService.getById(id, CategoryDto.class);
        req.setAttribute("category", dto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", urls = "/category/edit")
    public void editPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        CategoryDto dto = this.mapper.map(req, CategoryDto.class);
        dto = this.categoryService.update(dto, CategoryDto.class);
        this.redirectToHome(req, resp);
    }


    @Endpoint(urls = "/category/delete")
    public void deleteGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final CategoryDto dto = this.categoryService.getById(id, CategoryDto.class);
        req.setAttribute("category", dto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", urls = "/category/delete")
    public void deletePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        this.categoryService.remove(id);
        this.redirectToHome(req, resp);
    }


    @Endpoint(urls = "/category/details")
    public void detailsGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final CategoryProductsDto dto = this.categoryService.getById(id, CategoryProductsDto.class);
        req.setAttribute("category", dto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(urls = "/category/all")
    public void allGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final List<CategoryDto> dtos = this.categoryService.getAll(CategoryDto.class);
        req.setAttribute("categories", dtos);
        this.redirectToJsp(req, resp);
    }

}
