/*
 * ProductServlet.java
 *
 * created at 2019-10-30 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.servlet;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.shop.base.model.Product;
import com.example.shop.base.service.ProductService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(service = ProductController.class, //
                property = {"service.exported.interfaces=*", //
                            "service.exported.configs=org.apache.cxf.rs", //
                            "org.apache.cxf.rs.address=/product"})
@Path("")
public class ProductController
{
    @Reference
    private ProductService productService;


    @GET
    @Path("{id}")
    public Response getTask(@PathParam("id") Long id)
    {
        final Product product = this.productService.getById(id);
        return product == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(product).build();
    }

}
