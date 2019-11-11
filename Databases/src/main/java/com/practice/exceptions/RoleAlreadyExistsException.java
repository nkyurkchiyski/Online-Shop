/*
 * RoleAlreadyExistsException.java
 *
 * created at 2019-09-09 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.exceptions;


public class RoleAlreadyExistsException extends Exception
{
    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;


    public RoleAlreadyExistsException(String message)
    {
        super(message);
    }


    public RoleAlreadyExistsException(Throwable cause)
    {
        super(cause);
    }


    public RoleAlreadyExistsException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
