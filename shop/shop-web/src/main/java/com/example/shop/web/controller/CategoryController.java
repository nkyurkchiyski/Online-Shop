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
import com.example.shop.web.annotation.Request;
import com.example.shop.web.annotation.WebController;
import com.example.shop.web.constants.ShopEndpoint;
import com.example.shop.web.constants.RequestMethod;


@WebController(path = "/category")
@Service(classes = Controller.class)
@Bean(id = "categoryController")
public class CategoryController extends BaseController
{
    @Inject
    @Reference
    private CategoryService categoryService;


    @Request(endpoints = ShopEndpoint.CATEGORY_CREATE)
    public void createGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        this.forwardToJsp(ShopEndpoint.CATEGORY_CREATE, req, resp);
    }


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.CATEGORY_CREATE)
    public void createPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
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


    @Request(endpoints = ShopEndpoint.CATEGORY_EDIT)
    public void editGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            final Integer id = Integer.parseInt(req.getParameter("id"));
            final CategoryDto dto = this.categoryService.getById(id, CategoryDto.class);
            req.setAttribute("category", dto);
            this.forwardToJsp(ShopEndpoint.CATEGORY_EDIT, req, resp);
        }
        catch (final IllegalArgumentException e)
        {
            this.forwardToError(req, resp);
        }
    }


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.CATEGORY_EDIT)
    public void editPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        CategoryDto dto = this.mapper.map(req, CategoryDto.class);
        MessageDto messageDto = null;

        try
        {
            dto = this.categoryService.update(dto, CategoryDto.class);
            messageDto = new MessageDto(true, String.format(SuccessMessage.CATEGORY_EDIT_SUCCESSFUL, dto.getName()));
        }
        catch (final IllegalArgumentException e)
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


    @Request(endpoints = ShopEndpoint.CATEGORY_DELETE)
    public void deleteGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            final Integer id = Integer.parseInt(req.getParameter("id"));
            final CategoryDto dto = this.categoryService.getById(id, CategoryDto.class);
            req.setAttribute("category", dto);
            this.forwardToJsp(req, resp);
        }
        catch (final IllegalArgumentException e)
        {
            this.forwardToError(req, resp);
        }
    }


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.CATEGORY_DELETE)
    public void deletePost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        MessageDto messageDto = null;

        try
        {
            final Integer id = Integer.parseInt(req.getParameter("id"));
            this.categoryService.remove(id);
            messageDto = new MessageDto(true, SuccessMessage.CATEGORY_DELETE_SUCCESSFUL);
        }
        catch (final NumberFormatException e)
        {
            this.forwardToError(req, resp);
        }
        catch (final IllegalArgumentException e)
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


    @Request(endpoints = ShopEndpoint.CATEGORY_DETAILS)
    public void detailsGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            final Integer id = Integer.parseInt(req.getParameter("id"));
            final CategoryProductsDto dto = this.categoryService.getById(id, CategoryProductsDto.class);
            req.setAttribute("category", dto);
            this.forwardToJsp(req, resp);
        }
        catch (final IllegalArgumentException e)
        {
            this.forwardToError(req, resp);
        }
    }


    @Request(endpoints = ShopEndpoint.CATEGORY_ALL)
    public void allGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final int pageNumber = getCurrentPage(req);
        final int pages = this.categoryService.getAllPages();

        this.validatePage(resp, pageNumber, pages);

        final List<CategoryDto> dtos = this.categoryService.getAllPaginated(pageNumber < 0 ? 0 : pageNumber, CategoryDto.class);

        req.setAttribute("categories", dtos);
        req.setAttribute("currPage", pageNumber);
        req.setAttribute("pages", pages);

        this.forwardToJsp(ShopEndpoint.CATEGORY_ALL, req, resp);
    }
}
