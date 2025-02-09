/*
 * ErrorMessage.java
 *
 * created at 2019-11-26 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.constants;


public class ErrorMessage
{
    public static final String USER_LOGIN_UNSUCCESSFUL = "Invalid email or password!";
    public static final String USER_DOES_NOT_EXIST = "User does not exist!";
    public static final String USER_ALREADY_EXISTS = "User with the same email already exists!";

    public static final String PASSWORDS_NOT_MATCHING = "Passwords do not match!";
    public static final String PASSWORD_NOT_VALID = "Password must be between at least 6 charcters long and must include character, digit and symbol!";
    public static final String EMAIL_NOT_VALID = "Email is not valid!";

    public static final String MANDATORY_FIELDS = "%s fields are mandatory and cannot be empty!";

    public static final String PRODUCT_INVALID_PRICE = "The product price should be greater than 0!";
    public static final String PRODUCT_INVALID_QUANTITY = "The product quantity should be greater than 0!";

    public static final String SEARCH_MIN_MAX_PRICE = "The minimum price cannot be bigger than max price!";

    public static final String PRODUCT_DESCREASE_QUANTITY = "Quantity exceeds present product units!";
    public static final String PRODUCT_DOES_NOT_EXIST = "Product does not exist!";
    public static final String PRODUCT_NOT_AVAILABLE = "Product:%s is no longer available!";
    public static final String PRODUCT_NOT_ENOUGH_QUANTITY = "For Product:%s we have only %s units left!";

    public static final String PRODUCT_ORDER_DOES_NOT_EXIST = "ProductOrder does not exist!";

    public static final String CATEGORY_ALREADY_EXISTS = "Category with the same name already exists!";
    public static final String CATEGORY_DOES_NOT_EXISTS = "Category does not exist!";

    public static final String CART_DOES_NOT_EXIST = "Cart does not exist!";
    public static final String ORDER_DOES_NOT_EXIST = "Order does not exist!";

    public static final String RESULT_NOT_FOUND = "No result was found!";
    public static final String UNIQUE_RESULT_NOT_FOUND = "No unique result was found!";
    public static final String ROLE_DOES_NOT_EXIST = "Role does not exist!";

}
