/*
 * QueryUtil.java
 *
 * created at 2019-12-05 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.util;


public class QueryUtil
{
    private static final String PAGE_REGEX = "&page=\\d*";


    public static String getSearchQuery(final String query)
    {
        return query.replaceAll(PAGE_REGEX, "");
    }
}
