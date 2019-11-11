/*
 * UserService.java
 *
 * created at 2019-09-16 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.services;


import org.apache.log4j.Logger;

import com.practice.dao.UserDAO;
import com.practice.dao.UserDAOImpl;
import com.practice.entities.Role;
import com.practice.entities.User;
import com.practice.exceptions.NoSuchUserException;
import com.practice.exceptions.RelationAlreadyExistsException;
import com.practice.exceptions.UserNameAlreadyExistsException;
import com.practice.utilities.MessageConstants;


public class UserService implements IUserService
{
    private UserDAO userDAO;

    private static final Logger LOGGER = Logger.getLogger(UserService.class);


    public UserService()
    {
        this.userDAO = new UserDAOImpl();
    }


    @Override
    public User create(String userName, String password, byte[] pictureBytes) throws UserNameAlreadyExistsException
    {
        if (this.userDAO.exists(userName))
        {
            throw new UserNameAlreadyExistsException(String.format(MessageConstants.NAME_ALREADY_EXISTS_EXCEPTION, "User", userName));
        }

        if (this.userDAO.insert(new User(userName, password, pictureBytes)))
        {
            LOGGER.info(String.format(MessageConstants.ENTITY_SUCCESSFULLY_ADDED, "User"));
        }

        return this.find(userName);

    }


    @Override
    public User find(String userName)
    {
        return this.userDAO.getByName(userName);
    }


    @Override
    public void update(User user) throws UserNameAlreadyExistsException
    {
        if (user == null)
        {
            throw new IllegalArgumentException(String.format(MessageConstants.ENTITY_NOT_INITIALIZED_EXCEPTION, "User"));
        }

        final String userName = user.getUserName();

        if (this.userDAO.exists(userName) //
            && this.userDAO.getByName(userName).getUserId() != user.getUserId())
        {
            throw new UserNameAlreadyExistsException(String.format(MessageConstants.NAME_ALREADY_EXISTS_EXCEPTION, "User", userName));
        }

        if (this.userDAO.update(user))
        {
            LOGGER.info(String.format(MessageConstants.ENTITY_SUCCESSFULLY_UPDATED, "User"));
        }
    }


    @Override
    public void remove(User user) throws NoSuchUserException
    {
        if (user == null)
        {
            throw new IllegalArgumentException(String.format(MessageConstants.ENTITY_NOT_INITIALIZED_EXCEPTION, "User"));
        }

        final String userName = user.getUserName();

        if (!this.userDAO.exists(userName))
        {
            throw new NoSuchUserException(String.format(MessageConstants.NOT_PRESENT_EXCEPTION, "User", userName));
        }

        if (this.userDAO.delete(user))
        {
            LOGGER.info(String.format(MessageConstants.ENTITY_SUCCESSFULLY_DELETED, "User"));
        }
    }


    @Override
    public void addRole(User user, Role role) throws RelationAlreadyExistsException
    {
        if (user == null || role == null)
        {
            throw new IllegalArgumentException(String.format(MessageConstants.ENTITY_NOT_INITIALIZED_EXCEPTION, "User or Role"));
        }

        if (this.userDAO.existsUserRole(user, role))
        {
            throw new RelationAlreadyExistsException(String.format(MessageConstants.RELATION_ALREADY_EXISTS_EXCEPTION, "User", "Role"));
        }

        if (this.userDAO.addRole(user, role))
        {
            LOGGER.info(String.format(MessageConstants.RELATION_SUCCESSFULLY_ADDED, "User", "Role"));
        }
    }


    @Override
    public User seed(String userName, String password, byte[] pictureBytes)
    {
        User user = null;
        try
        {
            user = this.create(userName, password, pictureBytes);
        }
        catch (UserNameAlreadyExistsException e)
        {
            LOGGER.error(e.getMessage());
        }

        return user;
    }


    @Override
    public void seedUserRole(User user, Role role)
    {
        try
        {
            this.addRole(user, role);
        }
        catch (IllegalArgumentException | RelationAlreadyExistsException e)
        {
            LOGGER.error(e.getMessage());
        }
    }

}
