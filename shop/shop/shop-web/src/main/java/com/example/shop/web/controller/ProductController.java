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

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Reference;
import org.apache.aries.blueprint.annotation.service.Service;

import com.example.shop.base.constants.SuccessMessage;
import com.example.shop.base.dto.CategoryDto;
import com.example.shop.base.dto.MessageDto;
import com.example.shop.base.dto.ProductDto;
import com.example.shop.base.dto.ProductFormDto;
import com.example.shop.base.dto.ProductViewDto;
import com.example.shop.base.dto.SearchDto;
import com.example.shop.base.service.CategoryService;
import com.example.shop.base.service.ProductService;
import com.example.shop.web.annotation.Endpoint;
import com.example.shop.web.annotation.WebController;


@WebController(path = "/product")
@Service(classes = Controller.class)
@Bean(id = "productController")
public class ProductController extends BaseController
{
    @Inject
    @Reference
    private ProductService productService;

    @Inject
    @Reference
    private CategoryService categoryService;


    @Endpoint(urls = "/product/create")
    public void createGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final List<CategoryDto> categoryDtos = this.categoryService.getAll(CategoryDto.class);
        req.setAttribute("categories", categoryDtos);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(urls = "/product/edit")
    public void editGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final ProductViewDto productDto = this.productService.getById(id, ProductViewDto.class);
        final List<CategoryDto> categoryDtos = this.categoryService.getAll(CategoryDto.class);

        req.setAttribute("product", productDto);
        req.setAttribute("categories", categoryDtos);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(urls = "/product/delete")
    public void deleteGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final ProductViewDto dto = this.productService.getById(id, ProductViewDto.class);
        req.setAttribute("product", dto);
        this.redirectToJsp(req, resp);
    }


    @Endpoint(method = "post", urls = "/product/create")
    public void createPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final ProductFormDto dto = this.createProductFormDto(req);
        MessageDto messageDto = null;

        try
        {
            final ProductDto product = this.productService.create(dto, ProductDto.class);
            messageDto = new MessageDto(true, String.format(SuccessMessage.PRODUCT_CREATE_SUCCESSFUL, product.getName()));
        }
        catch (IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        req.setAttribute("message", messageDto);

        if (messageDto.isSuccessful())
        {
            this.allGet(req, resp);
        }
        else
        {
            this.createGet(req, resp);
        }
    }


    @Endpoint(method = "post", urls = "/product/edit")
    public void editPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final ProductFormDto dto = this.createProductFormDto(req);
        MessageDto messageDto = null;

        try
        {
            final ProductDto product = this.productService.update(dto, ProductDto.class);
            messageDto = new MessageDto(true, String.format(SuccessMessage.PRODUCT_EDIT_SUCCESSFUL, product.getName()));
        }
        catch (IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        req.setAttribute("message", messageDto);

        if (messageDto.isSuccessful())
        {
            this.allGet(req, resp);
        }
        else
        {
            this.editGet(req, resp);
        }
    }


    @Endpoint(method = "post", urls = "/product/delete")
    public void deletePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        MessageDto messageDto = null;

        try
        {
            this.productService.remove(id);
            messageDto = new MessageDto(true, SuccessMessage.PRODUCT_DELETE_SUCCESSFUL);
        }
        catch (IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
        }

        req.setAttribute("message", messageDto);

        if (messageDto.isSuccessful())
        {
            this.allGet(req, resp);
        }
        else
        {
            this.deleteGet(req, resp);
        }
    }


    @Endpoint(urls = "/product/details")
    public void detailsGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final ProductViewDto dto = this.productService.getById(id, ProductViewDto.class);
        req.setAttribute("product", dto);
        this.redirectToJsp("/product/details", req, resp);
    }


    @Endpoint(urls = "/product/all")
    public void allGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final List<ProductDto> productDtos = this.productService.getAll(ProductDto.class);
        req.setAttribute("products", productDtos);
        req.setAttribute("filter", "All");
        this.redirectToJsp("/product/all", req, resp);
    }


    @Endpoint(urls = "/product/search")
    public void searchGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final List<CategoryDto> categoryDtos = this.categoryService.getAll(CategoryDto.class);
        req.setAttribute("categories", categoryDtos);
        this.redirectToJsp("/product/search", req, resp);
    }


    @Endpoint(method = "post", urls = "/product/search")
    public void searchPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final SearchDto dto = this.createSearchDto(req);
        MessageDto messageDto = null;

        try
        {
            final List<ProductDto> productDtos = this.productService.getAllBySearch(dto, ProductDto.class);
            req.setAttribute("products", productDtos);
            req.setAttribute("filter", "Searched");
        }
        catch (IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
            req.setAttribute("message", messageDto);
        }

        if (messageDto == null)
        {
            this.redirectToJsp("/product/all", req, resp);
        }
        else
        {
            this.searchGet(req, resp);
        }
    }


    private ProductFormDto createProductFormDto(HttpServletRequest req)
    {
        final ProductFormDto dto = this.mapper.map(req, ProductFormDto.class);
        final String[] categoriesTemp = req.getParameterValues("categories");
        if (categoriesTemp != null)
            dto.setCategoryIds(Arrays.stream(categoriesTemp).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new));
        return dto;
    }


    private SearchDto createSearchDto(HttpServletRequest req)
    {
        final SearchDto dto = this.mapper.map(req, SearchDto.class);
        final String[] categoriesTemp = req.getParameterValues("categories");
        if (categoriesTemp != null)
            dto.setCategoryIds(Arrays.stream(categoriesTemp).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new));
        return dto;
    }

}
