/*
 * RightService.java
 *
 * created at 2019-09-16 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.services;


import org.apache.log4j.Logger;

import com.practice.dao.RightDAO;
import com.practice.dao.RightDAOImpl;
import com.practice.entities.Right;
import com.practice.exceptions.NoSuchRightException;
import com.practice.exceptions.RightAlreadyExistsException;
import com.practice.utilities.MessageConstants;


public class RightService implements IRightService
{
    private RightDAO rightDAO;

    private static final Logger LOGGER = Logger.getLogger(RightService.class);


    public RightService()
    {
        this.rightDAO = new RightDAOImpl();
    }


    @Override
    public Right create(String rightName) throws RightAlreadyExistsException
    {
        if (this.rightDAO.exists(rightName))
        {
            throw new RightAlreadyExistsException(String.format(MessageConstants.NAME_ALREADY_EXISTS_EXCEPTION, "Right", rightName));
        }

        if (this.rightDAO.insert(new Right(rightName)))
        {
            LOGGER.info(String.format(MessageConstants.ENTITY_SUCCESSFULLY_ADDED, "Right"));
        }

        return this.find(rightName);
    }


    @Override
    public Right find(String rightName)
    {
        return this.rightDAO.getByName(rightName);
    }


    @Override
    public void update(Right right) throws RightAlreadyExistsException
    {
        if (right == null)
        {
            throw new IllegalArgumentException(String.format(MessageConstants.ENTITY_NOT_INITIALIZED_EXCEPTION, "Right"));
        }

        final String rightName = right.getRightName();

        if (this.rightDAO.exists(rightName) //
            && this.rightDAO.getByName(rightName).getRightId() != right.getRightId())
        {
            throw new RightAlreadyExistsException(String.format(MessageConstants.NAME_ALREADY_EXISTS_EXCEPTION, "Right", rightName));
        }

        if (this.rightDAO.update(right))
        {
            LOGGER.info(String.format(MessageConstants.ENTITY_SUCCESSFULLY_UPDATED, "Right"));
        }
    }


    @Override
    public void remove(Right right) throws NoSuchRightException
    {
        if (right == null)
        {
            throw new IllegalArgumentException(String.format(MessageConstants.ENTITY_NOT_INITIALIZED_EXCEPTION, "Right"));
        }

        final String rightName = right.getRightName();

        if (!this.rightDAO.exists(rightName))
        {
            throw new NoSuchRightException(String.format(MessageConstants.NOT_PRESENT_EXCEPTION, "Right", rightName));
        }

        if (this.rightDAO.delete(right))
        {
            LOGGER.info(String.format(MessageConstants.ENTITY_SUCCESSFULLY_DELETED, "Right"));
        }
    }


    @Override
    public Right seed(String rightName)
    {
        Right right = null;

        try
        {
            right = this.create(rightName);
        }
        catch (RightAlreadyExistsException e)
        {
            LOGGER.error(e.getMessage());
        }

        return right;
    }

}
