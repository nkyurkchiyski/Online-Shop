/*
 * WebAppContext.java
 *
 * created at 2019-11-07 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.helper;


import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.context.ServletContextHelper;


@Component(service = ServletContextHelper.class, //
                property = {"osgi.http.whiteboard.context.name=online-shop", //
                            "osgi.http.whiteboard.context.path=/online-shop"})
public class ShopServletContext extends ServletContextHelper
{
}
