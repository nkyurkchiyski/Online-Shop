/*
 * DBRightDao.java
 *
 * created at 2019-09-12 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.practice.entities.Right;
import com.practice.utilities.DBCPDataSource;
import com.practice.utilities.Mapper;


public class RightDAOImpl implements RightDAO
{
    private static final Logger LOGGER = Logger.getLogger(RightDAOImpl.class);


    @Override
    public boolean insert(Right entity)
    {
        boolean success = false;
        final String sql = "INSERT INTO t_Rights(cRightName) VALUES(?);";

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.setString(1, entity.getRightName());
            stmt.executeUpdate();
            success = true;
        }
        catch (SQLException e)
        {
            LOGGER.error(e.getMessage());
        }

        return success;
    }


    @Override
    public boolean update(Right entity)
    {
        boolean success = false;
        final String sql = "UPDATE t_Rights SET cRightName = ? WHERE cRightId = ?;";

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, entity.getRightName());
            stmt.setInt(2, entity.getRightId());
            stmt.executeUpdate();
            success = true;
        }
        catch (SQLException e)
        {
            LOGGER.error(e.getMessage());
        }

        return success;
    }


    @Override
    public boolean delete(Right entity)
    {
        boolean success = false;
        final String sql = "DELETE FROM t_RoleRights WHERE cRoleRightsRightId = ? " //
                           + "DELETE FROM t_Rights WHERE cRightId = ?;";

        final int rightId = entity.getRightId();

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, rightId);
            stmt.setInt(2, rightId);
            stmt.executeUpdate();
            success = true;
        }
        catch (SQLException e)
        {
            LOGGER.error(e.getMessage());
        }

        return success;
    }


    @Override
    public boolean exists(String name)
    {
        boolean exists = false;
        final String sql = "SELECT TOP(1) cRightId FROM t_Rights WHERE cRightName = ?";

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next())
            {
                exists = true;
            }
        }
        catch (SQLException e)
        {
            LOGGER.error(e.getMessage());
        }

        return exists;
    }


    @Override
    public Right getByName(String name)
    {
        final String sql = "SELECT TOP(1) * FROM t_Rights WHERE cRightName = ?";

        Right right = null;
        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next())
                right = Mapper.mapRight(resultSet);

        }
        catch (SQLException e)
        {
            LOGGER.error(e.getMessage());
        }

        return right;
    }

}
