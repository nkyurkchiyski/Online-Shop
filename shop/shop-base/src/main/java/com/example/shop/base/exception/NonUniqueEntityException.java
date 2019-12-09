/*
 * NonUniqueModelException.java
 *
 * created at 2019-12-04 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.exception;


public class NonUniqueEntityException extends RuntimeException
{
    private static final long serialVersionUID = 1L;


    public NonUniqueEntityException(String message)
    {
        super(message);
    }


    public NonUniqueEntityException(Throwable cause)
    {
        super(cause);
    }


    public NonUniqueEntityException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
