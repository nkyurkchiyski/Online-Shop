/*
 * IRoleService.java
 *
 * created at 2019-09-16 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.services;


import com.practice.entities.Right;
import com.practice.entities.Role;
import com.practice.exceptions.NoSuchRoleException;
import com.practice.exceptions.RelationAlreadyExistsException;
import com.practice.exceptions.RoleAlreadyExistsException;


public interface IRoleService
{
    Role create(String roleName) throws RoleAlreadyExistsException;


    Role find(String roleName);


    void update(Role role) throws RoleAlreadyExistsException;


    void remove(Role role) throws NoSuchRoleException;


    void addRight(Role role, Right right) throws RelationAlreadyExistsException;


    Role seed(String roleName);


    void seedRoleRight(Role role, Right right);
}
