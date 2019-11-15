/*
 * ProductController.java
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
import com.example.shop.base.dto.ProductCreateDto;
import com.example.shop.base.dto.ProductDetailsDto;
import com.example.shop.base.service.CategoryService;
import com.example.shop.base.service.ProductService;
import com.example.shop.web.util.Endpoint;
import com.example.shop.web.util.ServiceUtil;


public class ProductController extends AbstractController
{
    private ProductService productService;
    private CategoryService categoryService;


    public ProductController()
    {
        this.productService = ServiceUtil.getService(ProductController.class, ProductService.class);
        this.categoryService = ServiceUtil.getService(ProductController.class, CategoryService.class);
    }


    @Endpoint(path = "/product/create")
    public void createGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final List<CategoryDto> categoryDtos = this.categoryService.getAll(CategoryDto.class);
        req.setAttribute("categories", categoryDtos);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(path = "/product/edit")
    public void editGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final ProductDetailsDto dto = this.productService.getById(id, ProductDetailsDto.class);
        req.setAttribute("product", dto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(path = "/product/delete")
    public void deleteGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final ProductDetailsDto dto = this.productService.getById(id, ProductDetailsDto.class);
        req.setAttribute("product", dto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", path = "/product/create")
    public void createPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ProductCreateDto dto = this.mapper.map(req, ProductCreateDto.class);
        final String[] categoriesTemp = req.getParameterValues("categories");
        //TODO map to integer array add to the dto
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", path = "/product/create")
    public void editPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", path = "/product/create")
    public void deletePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.redirectToJsp(req, resp);
    }
}
