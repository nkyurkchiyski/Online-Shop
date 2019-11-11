/*
 * NoSuchRoleException.java
 *
 * created at 2019-09-09 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.exceptions;


public class NoSuchRoleException extends Exception
{
    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;


    public NoSuchRoleException(String message)
    {
        super(message);
    }


    public NoSuchRoleException(Throwable cause)
    {
        super(cause);
    }


    public NoSuchRoleException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
