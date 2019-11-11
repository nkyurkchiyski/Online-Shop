/*
 * NoSuchUserException.java
 *
 * created at 2019-09-09 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.exceptions;


public class NoSuchUserException extends Exception
{
    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;


    public NoSuchUserException(String message)
    {
        super(message);
    }


    public NoSuchUserException(Throwable cause)
    {
        super(cause);
    }


    public NoSuchUserException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
