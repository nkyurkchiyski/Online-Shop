/*
 * NoSuchRightException.java
 *
 * created at 2019-09-09 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.exceptions;


public class NoSuchRightException extends Exception
{
    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;


    public NoSuchRightException(String message)
    {
        super(message);
    }


    public NoSuchRightException(Throwable cause)
    {
        super(cause);
    }


    public NoSuchRightException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
