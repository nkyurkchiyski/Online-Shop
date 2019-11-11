/*
 * IRightService.java
 *
 * created at 2019-09-16 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.services;


import com.practice.entities.Right;
import com.practice.exceptions.NoSuchRightException;
import com.practice.exceptions.RightAlreadyExistsException;


public interface IRightService
{
    Right create(String rightName) throws RightAlreadyExistsException;


    Right find(String rightName);


    void update(Right right) throws RightAlreadyExistsException;


    void remove(Right right) throws NoSuchRightException;


    Right seed(String rightName);

}
