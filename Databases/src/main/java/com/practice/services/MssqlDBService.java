/*
 * DBService.java
 *
 * created at 2019-09-09 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.services;


import com.practice.entities.*;
import com.practice.exceptions.*;


public class MssqlDBService implements DBService
{
    private IUserService userService;
    private IRightService rightService;
    private IRoleService roleService;


    public MssqlDBService(IUserService userService, IRoleService roleService, IRightService rightService)
    {
        this.rightService = rightService;
        this.roleService = roleService;
        this.userService = userService;
    }


    @Override
    public User createUser(final String userName, final String password, final byte[] pictureBytes) throws UserNameAlreadyExistsException
    {
        return this.userService.create(userName, password, pictureBytes);
    }


    @Override
    public Role createRole(final String roleName) throws RoleAlreadyExistsException
    {
        return this.roleService.create(roleName);
    }


    @Override
    public Right createRight(final String rightName) throws RightAlreadyExistsException
    {
        return this.rightService.create(rightName);
    }


    @Override
    public Right findRight(final String rightName)
    {
        return this.rightService.find(rightName);
    }


    @Override
    public Role findRole(final String roleName)
    {
        return this.roleService.find(roleName);
    }


    @Override
    public User findUser(final String userName)
    {
        return this.userService.find(userName);
    }


    @Override
    public void updateUser(User user) throws UserNameAlreadyExistsException
    {
        this.userService.update(user);
    }


    @Override
    public void updateRole(Role role) throws RoleAlreadyExistsException
    {
        this.roleService.update(role);
    }


    @Override
    public void updateRight(Right right) throws RightAlreadyExistsException
    {
        this.rightService.update(right);
    }


    @Override
    public void addRoleToUser(Role role, User user) throws RelationAlreadyExistsException
    {
        this.userService.addRole(user, role);
    }


    @Override
    public void addRightToRole(Right right, Role role) throws RelationAlreadyExistsException
    {
        this.roleService.addRight(role, right);
    }


    @Override
    public void deleteUser(User user) throws NoSuchUserException
    {
        this.userService.remove(user);
    }


    @Override
    public void deleteRole(Role role) throws NoSuchRoleException
    {
        this.roleService.remove(role);
    }


    @Override
    public void deleteRight(Right right) throws NoSuchRightException
    {
        this.rightService.remove(right);
    }


    public IUserService getUserService()
    {
        return userService;
    }


    public IRightService getRightService()
    {
        return rightService;
    }


    public IRoleService getRoleService()
    {
        return roleService;
    }

}
