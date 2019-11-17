/*
 * Path.java
 *
 * created at 2019-11-12 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EndpointUtil {
    private static final Pattern PATTERN = Pattern.compile("/[a-z]*");

    public static String getPath(final String uri, final String context) {
        return uri.replace(context, "");
    }

    public static String getControllerPath(final String fullPath) {
        final Matcher matcher = PATTERN.matcher(fullPath);

        if (!matcher.find()) {
            return null;
        }

        return matcher.group();
    }

}



