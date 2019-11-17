/*
 * ProductController.java
 *
 * created at 2019-11-14 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.controller;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.shop.base.dto.CategoryDto;
import com.example.shop.base.dto.ProductDto;
import com.example.shop.base.dto.ProductFormDto;
import com.example.shop.base.dto.ProductDetailsDto;
import com.example.shop.base.model.Product;
import com.example.shop.base.service.CategoryService;
import com.example.shop.base.service.ProductService;
import com.example.shop.web.annotation.Endpoint;
import com.example.shop.web.util.ServiceUtil;
import com.example.shop.web.annotation.WebController;


@WebController(path = "/product")
public class ProductController extends AbstractController {
    private ProductService productService;
    private CategoryService categoryService;


    public ProductController() {
        this.productService = ServiceUtil.getService(ProductController.class, ProductService.class);
        this.categoryService = ServiceUtil.getService(ProductController.class, CategoryService.class);
    }


    @Endpoint(urls = "/product/create")
    public void createGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<CategoryDto> categoryDtos = this.categoryService.getAll(CategoryDto.class);
        req.setAttribute("categories", categoryDtos);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(urls = "/product/edit")
    public void editGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO test
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final ProductDetailsDto dto = this.productService.getById(id, ProductDetailsDto.class);
        req.setAttribute("product", dto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(urls = "/product/delete")
    public void deleteGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO test
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final ProductDetailsDto dto = this.productService.getById(id, ProductDetailsDto.class);
        req.setAttribute("product", dto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", urls = "/product/create")
    public void createPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO add validation in service
        final ProductFormDto dto = getProductFormDto(req);
        final ProductDto product = this.productService.create(dto, ProductDto.class);
        resp.sendRedirect(String.format("/online-shop/product/details?id=%s", product.getId()));
    }


    @Endpoint(method = "post", urls = "/product/edit")
    public void editPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO test
        final ProductFormDto dto = getProductFormDto(req);
        final ProductDto product = this.productService.create(dto, ProductDto.class);
        resp.sendRedirect(String.format("/online-shop/product/details?id=%s", product.getId()));
    }

    @Endpoint(method = "post", urls = "/product/delete")
    public void deletePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO test
        final Integer id = Integer.parseInt(req.getParameter("id"));
        this.productService.remove(id);
        resp.sendRedirect("/online-shop/product/all");
    }

    @Endpoint(urls = "/product/details")
    public void detailsGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO test
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final ProductDetailsDto dto = this.productService.getById(id, ProductDetailsDto.class);
        req.setAttribute("product", dto);
        this.redirectToJsp(req, resp);
    }

    @Endpoint(urls = "/product/all")
    public void allGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO test
        final List<ProductDto> productDtos = this.productService.getAll(ProductDto.class);
        req.setAttribute("products", productDtos);
        this.redirectToJsp(req, resp);
    }

    private ProductFormDto getProductFormDto(HttpServletRequest req) {
        final ProductFormDto dto = this.mapper.map(req, ProductFormDto.class);
        final String[] categoriesTemp = req.getParameterValues("categories");
        dto.setCategoryIds(Arrays.stream(categoriesTemp).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new));
        return dto;
    }

}
