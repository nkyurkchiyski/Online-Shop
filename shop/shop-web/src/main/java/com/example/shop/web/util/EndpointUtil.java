/*
 * Path.java
 *
 * created at 2019-11-12 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.util;


public class EndpointUtil
{
    public static String getPath(final String uri, final String context)
    {
        return uri.replace(context, "");
    }
}



