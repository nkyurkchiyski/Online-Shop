/*
 * MssqlDBManager.java
 *
 * created at 2019-09-13 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.utilities;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;


public class MssqlDBManager implements DBManager
{
    private static final String IMPORT_SQL = "/db/create.sql";
    private Connection connection;

    private static final Logger LOGGER = Logger.getLogger(MssqlDBManager.class);


    public MssqlDBManager(Connection connection)
    {
        this.connection = connection;
    }


    @Override
    public void createDatabase()
    {
        try (Statement stmt = this.connection.createStatement())
        {
            final String sql = getSqlString();
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        }
        catch (SQLException | IOException e)
        {
            LOGGER.error(e.getMessage());
        }
    }


    @Override
    public void dropDatabase()
    {
        final String sql = "USE master "//
                           + "DROP DATABASE traineeDB;";

        try (Statement stmt = this.connection.createStatement())
        {
            stmt.executeUpdate(sql);
            System.out.println("Database deleted successfully...");
        }
        catch (SQLException e)
        {
            LOGGER.error(e.getMessage());
        }

    }


    private String getSqlString() throws IOException
    {
        String sqlString = null;
        try (InputStream inputStream = MssqlDBManager.class.getResourceAsStream(IMPORT_SQL))
        {
            sqlString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }

        return sqlString;
    }

}
