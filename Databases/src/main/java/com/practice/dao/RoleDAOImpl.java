/*
 * DBRoleDao.java
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
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.practice.entities.Right;
import com.practice.entities.Role;
import com.practice.entities.User;
import com.practice.utilities.DBCPDataSource;
import com.practice.utilities.Mapper;


public class RoleDAOImpl implements RoleDAO
{

    private static final Logger LOGGER = Logger.getLogger(RoleDAOImpl.class);


    @Override
    public boolean insert(Role entity)
    {
        boolean success = false;
        final String sql = "INSERT INTO t_Roles(cRoleName) VALUES(?);";

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, entity.getRoleName());
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
    public boolean update(Role entity)
    {
        boolean success = false;
        final String sql = "UPDATE t_Roles SET cRoleName = ? WHERE cRoleId = ?;";

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, entity.getRoleName());
            stmt.setInt(2, entity.getRoleId());
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
    public boolean delete(Role entity)
    {
        boolean success = false;
        final String sql = "DELETE FROM t_RoleRights WHERE cRoleRightsRoleId = ? " //
                           + "DELETE FROM t_UserRoles WHERE cUserRolesRoleId = ? " //
                           + "DELETE FROM t_Roles WHERE cRoleId = ?;";

        final int roleId = entity.getRoleId();
        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, roleId);
            stmt.setInt(2, roleId);
            stmt.setInt(3, roleId);
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
        final String sql = "SELECT TOP(1) cRoleId FROM t_Roles WHERE cRoleName = ?";

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
    public Role getByName(String name)
    {
        final String sql = "SELECT ro.cRoleId, ro.cRoleName, ri.cRightId, ri.cRightName FROM t_Roles ro " //
                           + "LEFT JOIN t_RoleRights rr ON rr.cRoleRightsRoleId = ro.cRoleId " //
                           + "LEFT JOIN t_Rights ri ON rr.cRoleRightsRightId = ri.cRightId " //
                           + "WHERE ro.cRoleName = ?";

        Role role = null;
        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next())
                role = Mapper.mapRole(resultSet);
        }
        catch (SQLException e)
        {
            LOGGER.error(e.getMessage());
        }

        return role;
    }


    public boolean addRight(Role role, Right right)
    {
        boolean success = false;
        final String sql = "INSERT INTO t_RoleRights(cRoleRightsRoleId, cRoleRightsRightId) " //
                           + "VALUES(?, ?);";

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, role.getRoleId());
            stmt.setInt(2, right.getRightId());
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
    public boolean existsRoleRight(Role role, Right right)
    {
        boolean exists = false;
        final String sql = "SELECT TOP(1) * FROM t_RoleRights " //
                           + "WHERE cRoleRightsRoleId = ? AND cRoleRightsRightId = ?";

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, role.getRoleId());
            stmt.setInt(2, right.getRightId());
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


    public Set<Role> getRolesOfUser(User user)
    {
        final String sql = "SELECT r.cRoleId, r.cRoleName FROM t_UserRoles ur " //
                           + "JOIN t_Roles r ON r.cRoleId = ur.cUserRolesRoleId " //
                           + "JOIN t_Users u ON u.cUserId = ur.cUserRolesUserId " //
                           + "WHERE u.cUserId = ?;";

        Set<Role> roles = new HashSet<Role>();

        try (Connection conn = DBCPDataSource.getConnection(); //
                        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, user.getUserId());
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next())
            {
                roles.add(Mapper.mapRole(resultSet));
            }
        }
        catch (SQLException e)
        {
            LOGGER.error(e.getMessage());
        }

        return roles;
    }

}
