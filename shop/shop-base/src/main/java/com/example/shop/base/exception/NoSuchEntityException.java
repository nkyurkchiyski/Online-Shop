/*
 * NoEntityFoundException.java
 *
 * created at 2019-12-04 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.exception;


public class NoSuchEntityException extends IllegalArgumentException
{
    private static final long serialVersionUID = 1L;


    public NoSuchEntityException(String message)
    {
        super(message);
    }


    public NoSuchEntityException(Throwable cause)
    {
        super(cause);
    }


    public NoSuchEntityException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
