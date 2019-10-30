/*
 * ProductServlet.java
 *
 * created at 2019-10-30 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.shop.base.model.Product;
import com.example.shop.base.service.ProductService;


public class ProductServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private ProductService productService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final PrintWriter writer = resp.getWriter();
        showAllProducts(writer);
    }


    private void showAllProducts(final PrintWriter writer)
    {
        writer.println("<h1>All Products</h1>");
        List<Product> products = productService.getAll();
        for (Product product : products)
        {
            writer.println(String.format("Id: %s, Name: %s, Num categoies: %s, ", product.getId(), product.getName(), product.getCategories().size()));
        }

    }


    public void setProductService(ProductService productService)
    {
        this.productService = productService;
    }

}
