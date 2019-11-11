/*
 * Mapper.java
 *
 * created at 2019-09-16 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.utilities;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.practice.entities.Right;
import com.practice.entities.Role;
import com.practice.entities.User;


public class Mapper
{
    public static User mapUser(ResultSet resultSet) throws SQLException
    {
        final int id = resultSet.getInt("cUserId");
        final String username = resultSet.getString("cUserName");
        final String password = resultSet.getString("cUserPassword");
        final Date createdAt = resultSet.getDate("cUserCreatedAt");
        final Date lastModifiedAt = resultSet.getDate("cUserLastModifiedAt");
        final boolean active = resultSet.getBoolean("cUserActive");
        final byte[] picture = resultSet.getBytes("cUserPicture");
        Set<Role> roles = new HashSet<Role>();

        do
        {
            roles.add(Mapper.mapRole(resultSet));
        }
        while (resultSet.next());

        return new User(id, username, password, createdAt, lastModifiedAt, picture, active, roles);
    }


    public static Role mapRole(ResultSet resultSet) throws SQLException
    {
        final int id = resultSet.getInt("cRoleId");
        final String roleName = resultSet.getString("cRoleName");
        Set<Right> rights = new HashSet<Right>();

        do
        {
            rights.add(Mapper.mapRight(resultSet));
        }
        while (resultSet.next() && roleName.equals(resultSet.getString("cRoleName")));

        return new Role(id, roleName, rights);
    }


    public static Right mapRight(ResultSet resultSet) throws SQLException
    {
        final int id = resultSet.getInt("cRightId");
        final String rightName = resultSet.getString("cRightName");
        return new Right(id, rightName);
    }

}
