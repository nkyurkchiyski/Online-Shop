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

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Reference;
import org.apache.aries.blueprint.annotation.service.Service;

import com.example.shop.base.constants.SuccessMessage;
import com.example.shop.base.dto.CategoryDto;
import com.example.shop.base.dto.CategoryProductsDto;
import com.example.shop.base.dto.MessageDto;
import com.example.shop.base.service.CategoryService;
import com.example.shop.web.annotation.Endpoint;
import com.example.shop.web.annotation.WebController;


@WebController(path = "/category")
@Service(classes = Controller.class)
@Bean(id = "categoryController")
public class CategoryController extends BaseController
{
    @Inject
    @Reference
    private CategoryService categoryService;


    @Endpoint(urls = "/category/create")
    public void createGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.redirectToJsp("/category/create", req, resp);
    }


    @Endpoint(method = "post", urls = "/category/create")
    public void createPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        CategoryDto dto = this.mapper.map(req, CategoryDto.class);
        MessageDto messageDto = null;

        try
        {
            dto = this.categoryService.create(dto, CategoryDto.class);
            messageDto = new MessageDto(true, String.format(SuccessMessage.CATEGORY_CREATE_SUCCESSFUL, dto.getName()));
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


    @Endpoint(urls = "/category/edit")
    public void editGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final CategoryDto dto = this.categoryService.getById(id, CategoryDto.class);
        req.setAttribute("category", dto);
        this.redirectToJsp("/category/edit", req, resp);
    }


    @Endpoint(method = "post", urls = "/category/edit")
    public void editPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        CategoryDto dto = this.mapper.map(req, CategoryDto.class);
        MessageDto messageDto = null;

        try
        {
            dto = this.categoryService.update(dto, CategoryDto.class);
            messageDto = new MessageDto(true, String.format(SuccessMessage.CATEGORY_EDIT_SUCCESSFUL, dto.getName()));
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
        MessageDto messageDto = null;

        try
        {
            this.categoryService.remove(id);
            messageDto = new MessageDto(true, SuccessMessage.CATEGORY_DELETE_SUCCESSFUL);
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
        final String pageNumberString = req.getParameter("page");

        final int pageNumber = pageNumberString == null ? 0 : Integer.parseInt(pageNumberString);
        final Integer pages = this.categoryService.getPages();

        if (pageNumber < 0 || pageNumber >= pages)
        {
            resp.sendRedirect("/online-shop/error");
        }

        final List<CategoryDto> dtos = this.categoryService.getAllPaginated(pageNumber < 0 ? 0 : pageNumber, CategoryDto.class);

        req.setAttribute("categories", dtos);
        req.setAttribute("currPage", pageNumber);
        req.setAttribute("pages", pages);

        this.redirectToJsp("/category/all", req, resp);
    }

}
