/*
 * ProductController.java
 *
 * created at 2019-11-14 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.controller;


import java.io.IOException;
import java.lang.reflect.Field;
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
import com.example.shop.base.dto.SearchFormDto;
import com.example.shop.base.service.CategoryService;
import com.example.shop.base.service.ProductService;
import com.example.shop.web.annotation.Request;
import com.example.shop.web.annotation.WebController;
import com.example.shop.web.constants.ShopEndpoint;
import com.example.shop.web.util.QueryUtil;
import com.example.shop.web.constants.RequestMethod;


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


    @Request(endpoints = ShopEndpoint.PRODUCT_CREATE)
    public void createGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final List<CategoryDto> categoryDtos = this.categoryService.getAll(CategoryDto.class);
        req.setAttribute("categories", categoryDtos);
        this.forwardToJsp(req, resp);
    }


    @Request(endpoints = ShopEndpoint.PRODUCT_EDIT)
    public void editGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            final Integer id = Integer.parseInt(req.getParameter("id"));
            final ProductViewDto productDto = this.productService.getById(id, ProductViewDto.class);
            final List<CategoryDto> categoryDtos = this.categoryService.getAll(CategoryDto.class);

            req.setAttribute("product", productDto);
            req.setAttribute("categories", categoryDtos);
            this.forwardToJsp(req, resp);
        }
        catch (final IllegalArgumentException e)
        {
            this.forwardToError(req, resp);
        }
    }


    @Request(endpoints = ShopEndpoint.PRODUCT_DELETE)
    public void deleteGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            final Integer id = Integer.parseInt(req.getParameter("id"));
            final ProductViewDto dto = this.productService.getById(id, ProductViewDto.class);
            req.setAttribute("product", dto);
            this.forwardToJsp(req, resp);
        }
        catch (final IllegalArgumentException e)
        {
            this.forwardToError(req, resp);
        }
    }


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.PRODUCT_CREATE)
    public void createPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        final ProductFormDto dto = this.createFormDto(req, ProductFormDto.class);
        MessageDto messageDto = null;

        try
        {
            final ProductDto product = this.productService.create(dto, ProductDto.class);
            messageDto = new MessageDto(true, String.format(SuccessMessage.PRODUCT_CREATE_SUCCESSFUL, product.getName()));
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
            this.createGet(req, resp);
        }
    }


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.PRODUCT_EDIT)
    public void editPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        final ProductFormDto dto = this.createFormDto(req, ProductFormDto.class);
        MessageDto messageDto = null;

        try
        {
            final ProductDto product = this.productService.update(dto, ProductDto.class);
            messageDto = new MessageDto(true, String.format(SuccessMessage.PRODUCT_EDIT_SUCCESSFUL, product.getName()));
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


    @Request(method = RequestMethod.POST, endpoints = ShopEndpoint.PRODUCT_DELETE)
    public void deletePost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        MessageDto messageDto = null;
        try
        {
            final Integer id = Integer.parseInt(req.getParameter("id"));
            this.productService.remove(id);
            messageDto = new MessageDto(true, SuccessMessage.PRODUCT_DELETE_SUCCESSFUL);
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


    @Request(endpoints = ShopEndpoint.PRODUCT_DETAILS)
    public void detailsGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            final Integer id = Integer.parseInt(req.getParameter("id"));
            final ProductViewDto dto = this.productService.getById(id, ProductViewDto.class);
            req.setAttribute("product", dto);
            this.forwardToJsp(ShopEndpoint.PRODUCT_DETAILS, req, resp);
        }
        catch (final IllegalArgumentException e)
        {
            this.forwardToError(req, resp);
        }
    }


    @Request(endpoints = ShopEndpoint.PRODUCT_ALL)
    public void allGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final int pageNumber = this.getCurrentPage(req);
        final int pages = this.productService.getAllPages();

        this.validatePage(resp, pageNumber, pages);

        final List<ProductDto> productDtos = this.productService.getAllPaginated(pageNumber < 0 ? 0 : pageNumber, ProductDto.class);
        this.setRequestAttributes(req, pageNumber, pages, "All", null, productDtos);

        this.forwardToJsp(ShopEndpoint.PRODUCT_ALL, req, resp);
    }


    @Request(endpoints = ShopEndpoint.PRODUCT_SEARCH_FORM)
    public void searchFormGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final List<CategoryDto> categoryDtos = this.categoryService.getAll(CategoryDto.class);
        req.setAttribute("categories", categoryDtos);
        this.forwardToJsp(ShopEndpoint.PRODUCT_SEARCH, req, resp);
    }


    @Request(endpoints = ShopEndpoint.PRODUCT_SEARCH)
    public void searchResultGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        final SearchFormDto dto = this.createFormDto(req, SearchFormDto.class);
        MessageDto messageDto = null;

        try
        {
            final int pageNumber = this.getCurrentPage(req);
            final int pages = this.productService.getAllBySearchPages(dto);

            this.validatePage(resp, pageNumber, pages);
            final List<ProductDto> productDtos = this.productService.getAllBySearchPaginated(pageNumber, dto, ProductDto.class);
            this.setRequestAttributes(req, pageNumber, pages, "Search", QueryUtil.getSearchQuery(req.getQueryString()), productDtos);
        }
        catch (final IllegalArgumentException e)
        {
            messageDto = new MessageDto(false, e.getMessage());
            req.setAttribute("message", messageDto);
        }

        if (messageDto == null)
        {
            this.forwardToJsp(ShopEndpoint.PRODUCT_ALL, req, resp);
        }
        else
        {
            this.searchFormGet(req, resp);
        }
    }


    private void setRequestAttributes(final HttpServletRequest req, final int pageNumber, final int pages, final String title, final String searchFilter, final List<ProductDto> dtos)
    {
        req.setAttribute("currPage", pageNumber);
        req.setAttribute("pages", pages);
        req.setAttribute("products", dtos);
        req.setAttribute("filter", title);
        req.setAttribute("searchFilter", searchFilter);
    }


    private <T> T createFormDto(final HttpServletRequest req, final Class<T> type) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        final T dto = this.mapper.map(req, type);
        final String[] categoriesTemp = req.getParameterValues("categories");
        if (categoriesTemp != null)
        {
            Field field = type.getDeclaredField("categoryIds");
            field.setAccessible(true);
            field.set(dto, Arrays.stream(categoriesTemp).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new));
        }
        return dto;
    }

}
