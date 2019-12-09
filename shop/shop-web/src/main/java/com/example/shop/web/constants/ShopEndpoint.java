/*
 * Endpoint.java
 *
 * created at 2019-12-04 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.constants;


public class ShopEndpoint
{
    public static final String HOME = "/home";
    public static final String ERROR = "/error";

    public static final String AUTH_LOGIN = "/auth/login";
    public static final String AUTH_LOGOUT = "/auth/logout";
    public static final String AUTH_REGISTER = "/auth/register";

    public static final String USER_EDIT = "/user/edit";
    public static final String USER_DELETE = "/user/delete";
    public static final String USER_PROFILE = "/user/profile";

    public static final String CART_DETAILS = "/cart/details";
    public static final String CART_PRODUCT = "/cart/product";
    public static final String CART_UPDATE = "/cart/update";

    public static final String CATEGORY_EDIT = "/category/edit";
    public static final String CATEGORY_DELETE = "/category/delete";
    public static final String CATEGORY_CREATE = "/category/create";
    public static final String CATEGORY_DETAILS = "/category/details";
    public static final String CATEGORY_ALL = "/category/all";

    public static final String PRODUCT_EDIT = "/product/edit";
    public static final String PRODUCT_DELETE = "/product/delete";
    public static final String PRODUCT_CREATE = "/product/create";
    public static final String PRODUCT_DETAILS = "/product/details";
    public static final String PRODUCT_ALL = "/product/all";
    public static final String PRODUCT_SEARCH_FORM = "/product/search/form";
    public static final String PRODUCT_SEARCH = "/product/search";

    public static final String ORDER_PLACE = "/order/place";
    public static final String ORDER_APPROVE = "/order/approve";
    public static final String ORDER_DETAILS = "/order/details";
    public static final String ORDER_INVOICE = "/order/invoice";
    public static final String ORDER_MANAGE = "/order/manage";
    public static final String ORDER_MY = "/order/my";

}
