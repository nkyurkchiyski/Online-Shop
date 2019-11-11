/*
 * UserNameAlreadyExistsException.java
 *
 * created at 2019-09-09 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.exceptions;


public class UserNameAlreadyExistsException extends Exception
{
    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;


    public UserNameAlreadyExistsException(String message)
    {
        super(message);
    }


    public UserNameAlreadyExistsException(Throwable cause)
    {
        super(cause);
    }


    public UserNameAlreadyExistsException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
