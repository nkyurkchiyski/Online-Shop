/*
 * StringUtil.java
 *
 * created at 2019-11-26 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.util;


public class StringUtil
{
    public static boolean isNullOrEmpty(final String input)
    {
        return input == null || input.trim().isEmpty();
    }
}
