/*
 * IUserService.java
 *
 * created at 2019-09-16 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.services;


import com.practice.entities.Role;
import com.practice.entities.User;
import com.practice.exceptions.NoSuchUserException;
import com.practice.exceptions.RelationAlreadyExistsException;
import com.practice.exceptions.UserNameAlreadyExistsException;


public interface IUserService
{
    User create(String userName, String password, byte[] pictureBytes) throws UserNameAlreadyExistsException;


    User find(String userName);


    void update(User user) throws UserNameAlreadyExistsException;


    void remove(User user) throws NoSuchUserException;


    void addRole(User user, Role role) throws RelationAlreadyExistsException;


    User seed(String userName, String password, byte[] pictureBytes);


    void seedUserRole(User user, Role role);

}
