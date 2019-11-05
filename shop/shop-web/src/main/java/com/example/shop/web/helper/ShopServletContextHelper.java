/*
 * ShopServletContextHelper.java
 *
 * created at 2019-11-04 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.helper;


import java.net.MalformedURLException;
import java.net.URL;

import org.osgi.service.component.annotations.*;
import org.osgi.service.http.context.ServletContextHelper;


@Component(service = ServletContextHelper.class, //
                property = {"osgi.http.whiteboard.context.name=online-shop", //
                            "osgi.http.whiteboard.context.path=/online-shop"})
public class ShopServletContextHelper extends ServletContextHelper
{

    public URL getResource(String name)
    {
        try
        {
            return new URL("http://localhost:8181/online-shop/" + name);
        }
        catch (MalformedURLException e)
        {
            return null;
        }
    }
}
