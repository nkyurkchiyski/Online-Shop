/*
 * RoleService.java
 *
 * created at 2019-09-16 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.services;


import org.apache.log4j.Logger;

import com.practice.dao.RoleDAO;
import com.practice.dao.RoleDAOImpl;
import com.practice.entities.Right;
import com.practice.entities.Role;
import com.practice.exceptions.NoSuchRoleException;
import com.practice.exceptions.RelationAlreadyExistsException;
import com.practice.exceptions.RoleAlreadyExistsException;
import com.practice.utilities.MessageConstants;


public class RoleService implements IRoleService
{
    private RoleDAO roleDAO;

    private static final Logger LOGGER = Logger.getLogger(RoleService.class);


    public RoleService()
    {
        this.roleDAO = new RoleDAOImpl();
    }


    @Override
    public Role create(String roleName) throws RoleAlreadyExistsException
    {
        if (this.roleDAO.exists(roleName))
        {
            throw new RoleAlreadyExistsException(String.format(MessageConstants.NAME_ALREADY_EXISTS_EXCEPTION, "Role", roleName));
        }

        if (this.roleDAO.insert(new Role(roleName)))
        {
            LOGGER.info(String.format(MessageConstants.ENTITY_SUCCESSFULLY_ADDED, "Role"));
        }

        return this.find(roleName);

    }


    @Override
    public Role find(String roleName)
    {
        return this.roleDAO.getByName(roleName);
    }


    @Override
    public void update(Role role) throws RoleAlreadyExistsException
    {
        if (role == null)
        {
            throw new IllegalArgumentException(String.format(MessageConstants.ENTITY_NOT_INITIALIZED_EXCEPTION, "Role"));
        }

        final String roleName = role.getRoleName();

        if (this.roleDAO.exists(roleName) //
            && this.roleDAO.getByName(roleName).getRoleId() != role.getRoleId())
        {
            throw new RoleAlreadyExistsException(String.format(MessageConstants.NAME_ALREADY_EXISTS_EXCEPTION, "Role", roleName));
        }

        if (this.roleDAO.update(role))
        {
            LOGGER.info(String.format(MessageConstants.ENTITY_SUCCESSFULLY_UPDATED, "Role"));
        }
    }


    @Override
    public void remove(Role role) throws NoSuchRoleException
    {
        if (role == null)
        {
            throw new IllegalArgumentException(String.format(MessageConstants.ENTITY_NOT_INITIALIZED_EXCEPTION, "Role"));
        }

        final String roleName = role.getRoleName();

        if (!this.roleDAO.exists(roleName))
        {
            throw new NoSuchRoleException(String.format(MessageConstants.NOT_PRESENT_EXCEPTION, "Role", roleName));
        }

        if (this.roleDAO.delete(role))
        {
            LOGGER.info(String.format(MessageConstants.ENTITY_SUCCESSFULLY_DELETED, "Role"));
        }
    }


    @Override
    public void addRight(Role role, Right right) throws RelationAlreadyExistsException
    {
        if (right == null || role == null)
        {
            throw new IllegalArgumentException(String.format(MessageConstants.ENTITY_NOT_INITIALIZED_EXCEPTION, "Role or Right"));
        }

        if (this.roleDAO.existsRoleRight(role, right))
        {
            throw new RelationAlreadyExistsException(String.format(MessageConstants.RELATION_ALREADY_EXISTS_EXCEPTION, "Role", "Right"));
        }

        if (this.roleDAO.addRight(role, right))
        {
            LOGGER.info(String.format(MessageConstants.RELATION_SUCCESSFULLY_ADDED, "Role", "Right"));
        }
    }


    @Override
    public Role seed(String roleName)
    {
        Role role = null;
        try
        {
            role = this.create(roleName);
        }
        catch (RoleAlreadyExistsException e)
        {
            LOGGER.error(e.getMessage());
        }

        return role;
    }


    @Override
    public void seedRoleRight(Role role, Right right)
    {
        try
        {
            this.addRight(role, right);
        }
        catch (IllegalArgumentException | RelationAlreadyExistsException e)
        {
            LOGGER.error(e.getMessage());
        }
    }

}
