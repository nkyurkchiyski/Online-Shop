/*
 * RightAlreadyExistsException.java
 *
 * created at 2019-09-09 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.exceptions;


public class RightAlreadyExistsException extends Exception
{
    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;


    public RightAlreadyExistsException(String message)
    {
        super(message);
    }


    public RightAlreadyExistsException(Throwable cause)
    {
        super(cause);
    }


    public RightAlreadyExistsException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
