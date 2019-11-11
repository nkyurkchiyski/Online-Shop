/*
 * DBService.java
 *
 * created at 2019-09-09 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.services;


import com.practice.entities.Right;
import com.practice.entities.Role;
import com.practice.entities.User;
import com.practice.exceptions.*;


public interface DBService
{
    User createUser(String userName, String password, byte[] pictureBytes) throws UserNameAlreadyExistsException;


    Role createRole(String roleName) throws RoleAlreadyExistsException;


    Right createRight(String rightName) throws RightAlreadyExistsException;


    Right findRight(String rightName);


    Role findRole(String roleName);


    User findUser(String userName);


    void updateUser(User user) throws UserNameAlreadyExistsException;


    void updateRole(Role role) throws RoleAlreadyExistsException;


    void updateRight(Right right) throws RightAlreadyExistsException;


    void addRoleToUser(Role role, User user) throws RelationAlreadyExistsException;


    void addRightToRole(Right right, Role role) throws RelationAlreadyExistsException;


    void deleteUser(User user) throws NoSuchUserException;


    void deleteRole(Role role) throws NoSuchRoleException;


    void deleteRight(Right right) throws NoSuchRightException;
}
