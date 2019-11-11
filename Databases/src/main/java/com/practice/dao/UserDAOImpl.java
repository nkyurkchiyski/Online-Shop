/*
 * DBUserDao.java
 *
 * created at 2019-09-12 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.dao;


import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.practice.entities.Role;
import com.practice.entities.User;
import com.practice.utilities.DBCPDataSource;
import com.practice.utilities.Mapper;


public class UserDAOImpl implements UserDAO
{
    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);


    @Override
    public boolean insert(User entity)
    {
        boolean success = false;
        final String sql = "INSERT INTO t_Users(cUserName, cUserPassword, cUserPicture) " //
                           + "VALUES(?, ?, ?);";

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, entity.getUserName());
            stmt.setString(2, entity.getPassword());
            stmt.setBinaryStream(3, new ByteArrayInputStream(entity.getPicture()));

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
    public boolean update(User entity)
    {
        boolean success = false;
        final String sql = "UPDATE t_Users SET cUserName = ?, cUserPassword = ?, " //
                           + "cUserLastModifiedAt = GETDATE(),cUserActive = ?,cUserPicture = ? " //
                           + "WHERE cUserId = ?";

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, entity.getUserName());
            stmt.setString(2, entity.getPassword());
            stmt.setBoolean(3, entity.isActive());
            stmt.setBinaryStream(4, new ByteArrayInputStream(entity.getPicture()));
            stmt.setInt(5, entity.getUserId());
            success = true;

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.error(e.getMessage());
        }

        return success;
    }


    @Override
    public boolean delete(User entity)
    {
        boolean success = false;
        final String sql = "DELETE FROM t_UserRoles WHERE cUserRolesUserId = ? " //
                           + "DELETE FROM t_Users WHERE cUserId = ?;";

        final int userId = entity.getUserId();

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, userId);
            stmt.setInt(2, userId);
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
        final String sql = "SELECT TOP(1) cUserId FROM t_Users WHERE cUserName = ?";

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
    public User getByName(String name)
    {
        final String sql = "SELECT * FROM t_Users u " //
                           + "LEFT JOIN t_UserRoles ur ON ur.cUserRolesUserId = u.cUserId " //
                           + "LEFT JOIN t_Roles ro ON ur.cUserRolesRoleId = ro.cRoleId " //
                           + "LEFT JOIN t_RoleRights rr ON rr.cRoleRightsRoleId = ro.cRoleId " //
                           + "LEFT JOIN t_Rights ri ON rr.cRoleRightsRightId = ri.cRightId " //
                           + "WHERE u.cUserName = ?";

        User user = null;
        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next())
                user = Mapper.mapUser(resultSet);
        }
        catch (SQLException e)
        {
            LOGGER.error(e.getMessage());
        }

        return user;
    }


    public boolean addRole(User user, Role role)
    {
        boolean success = false;
        final String sql = "INSERT INTO t_UserRoles(cUserRolesUserId, cUserRolesRoleId) " //
                           + "VALUES(?, ?);";

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, user.getUserId());
            stmt.setInt(2, role.getRoleId());
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
    public boolean existsUserRole(User user, Role role)
    {
        boolean exists = false;
        final String sql = "SELECT TOP(1) * FROM t_UserRoles " //
                           + "WHERE cUserRolesUserId = ? AND cUserRolesRoleId = ?";

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, user.getUserId());
            stmt.setInt(2, role.getRoleId());
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

}
