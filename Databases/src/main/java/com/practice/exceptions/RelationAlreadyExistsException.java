/*
 * RelationAlreadyExistsException.java
 *
 * created at 2019-09-09 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.exceptions;


public class RelationAlreadyExistsException extends Exception
{
    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;


    public RelationAlreadyExistsException(String message)
    {
        super(message);
    }


    public RelationAlreadyExistsException(Throwable cause)
    {
        super(cause);
    }


    public RelationAlreadyExistsException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
